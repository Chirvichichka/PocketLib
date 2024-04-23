package com.chirvi.pocketlib.presentation.ui.screen.introduction.login

sealed class IntroductionLoginState {
    data object Initial : IntroductionLoginState()
    data object Loading : IntroductionLoginState()
    data object Complete : IntroductionLoginState()
}