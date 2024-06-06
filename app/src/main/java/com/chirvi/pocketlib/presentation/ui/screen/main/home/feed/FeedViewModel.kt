package com.chirvi.pocketlib.presentation.ui.screen.main.home.feed

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.posts.GetAllBooksUseCase
import com.chirvi.domain.usecase.settings.GetSettingsUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val getAllBooksUseCase: GetAllBooksUseCase,
) : ViewModel()
{
    private val _state = MutableLiveData<FeedState>(FeedState.Initial)
    val state: LiveData<FeedState> = _state

    private val _postsList = MutableLiveData<List<BookPresentation>>()
    val postsList: LiveData<List<BookPresentation>> = _postsList

    private val _newText = MutableLiveData("")
    val newText: LiveData<String> = _newText

    private val _feedDisplayMode = MutableLiveData(loadFeedDisplayMode())
    val feedDisplayMode: LiveData<DisplayMode> = _feedDisplayMode
    private fun loadFeedDisplayMode() : DisplayMode { return getSettingsUseCase(key = "FEED") }
    fun textChange(text: String) { _newText.value = text }
    fun loadData() { viewModelScope.launch{ suspendLoadData() } }

    private suspend fun suspendLoadData() {
        _state.value = FeedState.Loading
        viewModelScope.launch {
            val bookListDomain = getAllBooksUseCase()
            val bookLIstPresentation = mutableListOf<BookPresentation>()
            bookListDomain.forEach { bookLIstPresentation.add(it.toPresentation()) }
            _postsList.value = bookLIstPresentation
        }.join()
        _state.value = FeedState.Content
    }

    private val database = FirebaseDatabase.getInstance()
    private val postsReference = database.getReference("posts")

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _state.value = FeedState.Loading
            val endQuery = query + "\uf8ff"
            val query1 = postsReference.orderByChild("name").startAt(query).endAt(endQuery)
            val dataSnapshot = query1.get().await()
            val bookListBy = mutableListOf<BookPresentation>()
            for (snapshot in dataSnapshot.children.reversed()) {
                bookListBy.add(
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
            _postsList.value = bookListBy
            _state.value = FeedState.Content
        }
    }

    init {
        loadData()
    }
}

