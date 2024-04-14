package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user

sealed class UserState {
    data object Initial : UserState()
    data object Loading : UserState()
    data object Content : UserState()
}