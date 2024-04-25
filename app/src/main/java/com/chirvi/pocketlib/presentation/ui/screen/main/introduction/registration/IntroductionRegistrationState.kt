package com.chirvi.pocketlib.presentation.ui.screen.main.introduction.registration

sealed interface IntroductionRegistrationState {
    data object Initial : IntroductionRegistrationState
    data object Loading : IntroductionRegistrationState
    data object Complete : IntroductionRegistrationState
}