package com.chirvi.pocketlib.presentation.ui.screen.book_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.pocketlib.presentation.models.Book
import com.chirvi.pocketlib.presentation.navigation.state.BookPageScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookPageViewModel @Inject constructor() : ViewModel() {

    private val _screenState = MutableLiveData<BookPageScreenState>(BookPageScreenState.Initial)
    val screenState: LiveData<BookPageScreenState> = _screenState

    fun loadBookPage() {
        _screenState.value = BookPageScreenState.BookPage
    }

}