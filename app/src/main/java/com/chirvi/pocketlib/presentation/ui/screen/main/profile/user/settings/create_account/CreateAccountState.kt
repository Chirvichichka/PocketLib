package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.create_account

sealed class CreateAccountState {
    data object Initial : CreateAccountState()
    data object Loading : CreateAccountState()
    data object Complete : CreateAccountState()
}