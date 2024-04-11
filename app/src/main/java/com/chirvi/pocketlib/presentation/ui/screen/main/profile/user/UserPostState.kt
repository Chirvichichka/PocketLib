package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user

sealed class UserPostState {
    data object Initial : UserPostState()
    data object Loading : UserPostState()
    data object Content : UserPostState()
}