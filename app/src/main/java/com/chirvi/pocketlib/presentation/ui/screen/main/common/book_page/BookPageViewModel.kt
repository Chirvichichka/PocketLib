package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.posts.GetBookByIdUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class BookPageViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase
) : ViewModel() {
    private val _state = MutableLiveData<BookPageState>(BookPageState.Initial)
    val state: LiveData<BookPageState> = _state

    private val _book = MutableLiveData(BookPresentation())
    val book: LiveData<BookPresentation> = _book

    private val _favorites = MutableLiveData<List<String>>(emptyList())
    val favorites: LiveData<List<String>> = _favorites

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun toggleIsFavorite() {
        _isFavorite.value = !_isFavorite.value!!
    }

    fun navigateToBack(navigation: NavigationState, currentRoute: String) {
        when(currentRoute) {
            "book_page_feed/{feed_post_id}" -> { navigation.navHostController.popBackStack(inclusive = true, route = Screen.BookFeed.route) }
            "book_page_profile/{profile_post_id}" -> { navigation.navHostController.popBackStack(inclusive = true, route = Screen.BookProfile.route) }
        }
    }

    fun getFavorites(idUser: String, idBook: String) {
        val database = FirebaseDatabase.getInstance()
        val usersReference = database.getReference("users")
        val userFavoritesReference = usersReference.child(idUser).child("favorites")
        viewModelScope.launch {
            _favorites.value = userFavoritesReference.get().await().children.map { it.value.toString() }
        }
        _isFavorite.value = favorites.value?.contains(idBook) == true
    }

    fun toggleFavorite(idBook: String, idUser: String) {
        val database = FirebaseDatabase.getInstance()
        val usersReference = database.getReference("users")
        val userFavoritesReference = usersReference.child(idUser).child("favorites")
        viewModelScope.launch {
            _favorites.value = userFavoritesReference.get().await().children.map { it.value.toString() }
        }
        userFavoritesReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val currentFavorites = dataSnapshot.getValue(object : GenericTypeIndicator<List<String>>() {}) ?: emptyList()
                val updatedFavorites = currentFavorites.toMutableList()
                if (updatedFavorites.contains(idBook)) {
                    updatedFavorites.remove(idBook)
                } else {
                    updatedFavorites.add(idBook)
                }

                userFavoritesReference.setValue(updatedFavorites)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }

    fun navigateToBookViewer(navigation: NavigationState, currentRoute: String) {
        val bookId = book.value?.id?:""
        when(currentRoute) {
            "book_page_feed/{feed_post_id}" -> { navigation.navigateToBookViewerFromFeed(bookId) }
            "book_page_profile/{profile_post_id}" -> { navigation.navigateToBookViewerFromProfile(bookId) }
        }
    }

    fun getBookById(id: String) {
        BookPageState.Loading
        viewModelScope.launch {
            _book.value = getBookByIdUseCase(id)?.toPresentation() ?: BookPresentation()
            _state.value = BookPageState.Content
        }
    }
}
