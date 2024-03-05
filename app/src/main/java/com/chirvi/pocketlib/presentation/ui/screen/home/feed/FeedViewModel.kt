package com.chirvi.pocketlib.presentation.ui.screen.home.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.models.Book
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.usecase.settings.GetSettingsFeedUseCase
import com.chirvi.pocketlib.presentation.models.BookItemMapper
import com.chirvi.pocketlib.presentation.models.BookPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getSettingsFeedUseCase: GetSettingsFeedUseCase,
    private val postsRepository: PostsRepository,
) : ViewModel()
{
    private val _postsList = MutableLiveData<List<BookPresentation>>()
    val postsList: LiveData<List<BookPresentation>> = _postsList

    private val _newText = MutableLiveData("")
    val newText: LiveData<String> = _newText

    private val _feedDisplayMode = MutableLiveData(loadFeedSettings())
    val feedDisplayMode: LiveData<DisplayMode> = _feedDisplayMode
    private fun loadFeedSettings() : DisplayMode {
        val displayMode = getSettingsFeedUseCase()
        return displayMode
    }

    init {
        viewModelScope.launch {
            getPostsList()
        }
        Log.e("AAA", postsList.value.toString())
    }

    private suspend fun getPostsList() {
        val domainBooks = postsRepository.getAllBooks()
        val presentationBooks = domainBooks.map { BookItemMapper.fromDomain(it) }
        _postsList.value = presentationBooks
        Log.e("AAA", presentationBooks.toString())
    }

    fun textChange(text: String) { _newText.value = text }
}