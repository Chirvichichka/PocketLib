package com.chirvi.pocketlib.presentation.ui.screen.main.introduction.login

sealed interface IntroductionLoginState {
    data object Initial : IntroductionLoginState
    data object Loading : IntroductionLoginState
    data object Complete : IntroductionLoginState
}