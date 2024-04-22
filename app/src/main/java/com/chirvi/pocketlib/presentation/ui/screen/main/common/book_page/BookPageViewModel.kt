package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.posts.GetBookByIdUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookPageViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase
) : ViewModel() {
    private val _state = MutableLiveData<BookPageState>(BookPageState.Initial)
    val state: LiveData<BookPageState> = _state

    private val _book = MutableLiveData(BookPresentation())
    val book: LiveData<BookPresentation> = _book

    fun getBookById(id: String) {
        BookPageState.Loading
        viewModelScope.launch {
            _book.value = getBookByIdUseCase(id)?.toPresentation() ?: BookPresentation()
            _state.value = BookPageState.Content
        }
    }
}
