package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page.book_viewer

sealed class BookViewerState {
    data object Initial : BookViewerState()
    data object Loading : BookViewerState()
    data object Content : BookViewerState()
}