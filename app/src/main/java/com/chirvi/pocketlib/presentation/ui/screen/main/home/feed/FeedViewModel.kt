package com.chirvi.pocketlib.presentation.ui.screen.main.home.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.posts.GetAllBooksUseCase
import com.chirvi.domain.usecase.settings.GetSettingsFeedUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getSettingsFeedUseCase: GetSettingsFeedUseCase,
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
    private fun loadFeedDisplayMode() : DisplayMode { return getSettingsFeedUseCase() }
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
        Log.e("update", postsList.value.toString())
    }
    init {
        loadData()
    }
}

