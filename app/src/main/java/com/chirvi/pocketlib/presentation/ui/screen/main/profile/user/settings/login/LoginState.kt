package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.login

sealed interface LoginState {
    data object Initial : LoginState
    data object Loading : LoginState
    data object Complete : LoginState
}