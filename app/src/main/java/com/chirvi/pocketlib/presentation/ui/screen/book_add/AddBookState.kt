package com.chirvi.pocketlib.presentation.ui.screen.book_add

sealed class AddBookState {
    data object Initial : AddBookState()
    data object Loading : AddBookState()
    data object Saved : AddBookState()
}
