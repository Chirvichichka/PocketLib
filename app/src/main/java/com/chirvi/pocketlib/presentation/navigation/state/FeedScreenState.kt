package com.chirvi.pocketlib.presentation.navigation.state

sealed class FeedScreenState {

    data object Initial : FeedScreenState()
    data object Feed : FeedScreenState()

}