package com.chirvi.pocketlib.presentation.ui.screen.main.home.feed

sealed class FeedState {
    data object Initial : FeedState()
    data object Loading : FeedState()
    data object Content : FeedState()
}