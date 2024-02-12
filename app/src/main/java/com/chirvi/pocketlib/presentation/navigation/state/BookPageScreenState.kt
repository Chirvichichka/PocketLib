package com.chirvi.pocketlib.presentation.navigation.state

sealed class BookPageScreenState {

    data object Initial : BookPageScreenState()
    data object BookPage : BookPageScreenState()

}