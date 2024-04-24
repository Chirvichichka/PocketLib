package com.chirvi.pocketlib.presentation.ui.screen.main.introduction.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.users.AuthenticationUseCase
import com.chirvi.pocketlib.presentation.models.UserPresentation
import com.chirvi.pocketlib.presentation.models.toDomain
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroductionLoginViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {
    private val _state = MutableLiveData<IntroductionLoginState>(IntroductionLoginState.Initial)
    val state: LiveData<IntroductionLoginState> = _state

    private val _textEmail = MutableLiveData("")
    val textEmail: LiveData<String> = _textEmail

    private val _textPassword = MutableLiveData("")
    val textPassword: LiveData<String> = _textPassword

    fun onValueChangeEMail(text: String) { _textEmail.value = text }
    fun onValueChangePassword(text: String) { _textPassword.value = text }

    fun navigateToRegistration(navigationState: NavigationState) {
        navigationState.navigateTo(Screen.IntroductionRegistration.route)
    }

    fun authentication() {
        _state.value = IntroductionLoginState.Loading
        viewModelScope.launch {
            val user = UserPresentation(
                email = textEmail.value?:"",
                password = textPassword.value?:""
            ).toDomain()
            try {
                authenticationUseCase(user)
                _state.value = IntroductionLoginState.Complete
            } catch (e: Exception) {
                _state.value = IntroductionLoginState.Initial
            }
        }
    }
}
