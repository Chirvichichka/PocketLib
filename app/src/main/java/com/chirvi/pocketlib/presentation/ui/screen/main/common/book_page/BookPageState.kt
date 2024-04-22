package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

sealed class BookPageState {
    data object Initial : BookPageState()
    data object Loading : BookPageState()
    data object Content : BookPageState()
}