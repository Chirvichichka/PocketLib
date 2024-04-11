package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.posts.GetAllBooksUseCase
import com.chirvi.domain.usecase.settings.GetSettingsUseCase
import com.chirvi.pocketlib.presentation.constants.DisplayModeKeys
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import com.chirvi.pocketlib.presentation.ui.screen.main.home.feed.FeedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val getAllBooksUseCase: GetAllBooksUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<UserPostState>(UserPostState.Initial)
    val state: LiveData<UserPostState> = _state

    private val _image = MutableLiveData(Uri.EMPTY)
    val image: LiveData<Uri?> = _image

    private val _postsList = MutableLiveData<List<BookPresentation>>()
    val postsList: LiveData<List<BookPresentation>> = _postsList

    private val _tabRowIndex = MutableLiveData(0)
    val tabRowItem: LiveData<Int> = _tabRowIndex

    fun onIndexChange(index: Int) { _tabRowIndex.value = index }

    private val _myBooksDisplayMode = MutableLiveData(loadMyBooksSettings())
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

    private fun loadData() { viewModelScope.launch{ suspendLoadData() } }
    private suspend fun suspendLoadData() {
        _state.value = UserPostState.Loading
        viewModelScope.launch {
            val bookListDomain = getAllBooksUseCase()
            val bookLIstPresentation = mutableListOf<BookPresentation>()
            bookListDomain.forEach { bookLIstPresentation.add(it.toPresentation()) }
            _postsList.value = bookLIstPresentation
        }.join()
        _state.value = UserPostState.Content
    }
    init {
        loadData()
    }
}