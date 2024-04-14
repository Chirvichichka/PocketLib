package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.login

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
class LoginViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {
    private val _state = MutableLiveData<LoginState>(LoginState.Initial)
    val state: LiveData<LoginState> = _state

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    private val _textEMail = MutableLiveData("")
    val textEMail: LiveData<String> = _textEMail

    private val _textPassword = MutableLiveData("")
    val textPassword: LiveData<String> = _textPassword

    fun toProfile(navigationState: NavigationState) {
        navigationState.navigateTo(Screen.Profile.route)
    }

    fun authentication() {
        viewModelScope.launch { suspendAuthentication() }
    }

    private suspend fun suspendAuthentication() {
        _state.value = LoginState.Loading
        viewModelScope.launch {
            val user = UserPresentation(
                email = textEMail.value?:"",
                password = textPassword.value?:""
            ).toDomain()
            try {
                authenticationUseCase(user)
                _state.value = LoginState.Complete
            } catch (e: Exception) {
                _state.value = LoginState.Initial
                _errorMessage.value = "Некорректный ввод данных"
            }
        }.join()

    }

    fun onValueChangeEMail(text: String) { _textEMail.value = text }
    fun onValueChangePassword(text: String) { _textPassword.value = text }
}