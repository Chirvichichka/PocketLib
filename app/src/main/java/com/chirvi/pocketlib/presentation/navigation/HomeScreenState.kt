package com.chirvi.pocketlib.presentation.navigation

sealed class HomeScreenState {

    data object Initial : HomeScreenState()
    data object Feed : HomeScreenState()
    data object Post : HomeScreenState()

}