package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.BookReaderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewerViewModel @Inject constructor(
    private val bookReaderUseCase: BookReaderUseCase,
) : ViewModel() {
    private val _text = MutableLiveData<List<String>>(emptyList())
    val text : LiveData<List<String>> = _text

    private val chapterCount: Int

    private val _currentChapter = MutableLiveData(0)
    val currentChapter : LiveData<Int> = _currentChapter

    private var offsetX = 0f

    private val _scrollState = MutableLiveData(ScrollState(0))
    val scrollState: LiveData<ScrollState> = _scrollState

    suspend fun animateScrollToTop(scrollState: ScrollState) {
        scrollState.animateScrollTo(0)
    }

    fun offsetXChange(delta: Float) {
        offsetX = offsetX.plus(delta)
    }

    fun drag() {
        if (offsetX > 300f) {
            currentChapterDown()
            offsetX = 0f
        } else if(offsetX < -300) {
            currentChapterUp()
            offsetX = 0f
        }
    }

    private fun currentChapterDown() {
        val current = _currentChapter.value?:0
        if (current != 0) {
            _currentChapter.value = current - 1
        }

        viewModelScope.launch {
            _scrollState.value?.scrollTo(0)
        }
    }

    private fun currentChapterUp() {
        val current = _currentChapter.value?:0
        if (current != chapterCount - 1) {
            _currentChapter.value = current + 1
        }
        viewModelScope.launch {
            _scrollState.value?.scrollTo(0)
        }
    }

    fun downloadBook(id: String) {
        viewModelScope.launch {
            suspendDownloadBook(id)
        }
    }

    suspend fun suspendDownloadBook(id: String) {
        viewModelScope.launch {
            _text.value = bookReaderUseCase(id)
        }.join()


    }
    init {
        chapterCount = _text.value?.size?:0
    }
}