package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.posts.GetUserBooksUseCase
import com.chirvi.domain.usecase.settings.GetSettingsUseCase
import com.chirvi.pocketlib.presentation.constants.DisplayModeKeys
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val getUserBooksUseCase: GetUserBooksUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<UserState>(UserState.Initial)
    val state: LiveData<UserState> = _state

    private val _image = MutableLiveData(Uri.EMPTY)
    val image: LiveData<Uri?> = _image

    private val _postsList = MutableLiveData<List<BookPresentation>>(emptyList())
    val postsList: LiveData<List<BookPresentation>> = _postsList

    private val _tabRowIndex = MutableLiveData(0)
    val tabRowItem: LiveData<Int> = _tabRowIndex

    fun onIndexChange(index: Int) { _tabRowIndex.value = index }

    private val _myBooksDisplayMode = MutableLiveData(loadMyBooksSettings()) //todo z
    val myBooksDisplayMode: LiveData<DisplayMode> = _myBooksDisplayMode
    private fun loadMyBooksSettings() : DisplayMode {
        val displayMode = getSettingsUseCase(DisplayModeKeys.MY_BOOKS_KEY)
        return displayMode
    }

    private val _favoritesDisplayMode = MutableLiveData(loadFavoritesSettings())
    val favoritesDisplayMode: LiveData<DisplayMode> = _favoritesDisplayMode
    private fun loadFavoritesSettings() : DisplayMode {
        val displayMode = getSettingsUseCase(DisplayModeKeys.FAVORITES_KEY)
        return displayMode
    }

    private val _favoritesBooks = MutableLiveData<List<BookPresentation>>(emptyList())
    val favoritesBooks: LiveData<List<BookPresentation>> = _favoritesBooks

    fun getFavoritesBooks(idUser: String) {
        val database = FirebaseDatabase.getInstance()
        val postsReference = database.getReference("posts")
        val usersReference = database.getReference("users")
        val userFavoritesReference = usersReference.child(idUser).child("favorites")

        viewModelScope.launch {
            // Получаем список избранных книг
            val favorites = userFavoritesReference.get().await().children.map { it.value.toString() }

            val userBookList = mutableListOf<BookPresentation>()

            // Для каждой избранной книги делаем запрос на получение данных о книге
            favorites.forEach { favoriteBook ->
                val query = postsReference.orderByChild("id").equalTo(favoriteBook)
                val dataSnapshot = query.get().await()
                for (snapshot in dataSnapshot.children) {
                    userBookList.add(
                        BookDomain(
                            id = snapshot.child("id").value.toString(),
                            userId = snapshot.child("userId").value.toString(),
                            name = snapshot.child("name").value.toString(),
                            author = snapshot.child("author").value.toString(),
                            description = snapshot.child("description").value.toString(),
                            genres = snapshot.child("genres").children.map { it.value.toString() },
                            image = snapshot.child("image").value.toString()
                        ).toPresentation()
                    )
                }
            }

            // Обновляем состояние
            _favoritesBooks.value = userBookList
        }
    }


    fun navigateToSettings(navigation: NavigationState) {
        navigation.navigateTo(Screen.Settings.route)
    }

    private fun loadData() {
        viewModelScope.launch{ suspendLoadData() }
    }
    private suspend fun suspendLoadData() {
        _state.value = UserState.Loading
        val currentUserId = Firebase.auth.currentUser?.uid
        viewModelScope.launch {
            val bookListDomain = getUserBooksUseCase(currentUserId?:"")
            val bookLIstPresentation = mutableListOf<BookPresentation>()
            bookListDomain.forEach { bookLIstPresentation.add(it.toPresentation()) }
            _postsList.value = bookLIstPresentation
        }.join()
        _state.value = UserState.Content
    }
    init {
        loadData()
    }
}