package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.auth.AuthenticationUseCase
import com.chirvi.pocketlib.presentation.models.UserPresentation
import com.chirvi.pocketlib.presentation.models.toDomain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {
    private val _state = MutableLiveData<LoginState>(LoginState.Initial)
    val state: LiveData<LoginState> = _state

    private val _textEMail = MutableLiveData("")
    val textEMail: LiveData<String> = _textEMail

    private val _textPassword = MutableLiveData("")
    val textPassword: LiveData<String> = _textPassword


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
            authenticationUseCase(user)
        }.join()
        _state.value = LoginState.Complete
    }

    fun onValueChangeEMail(text: String) { _textEMail.value = text }
    fun onValueChangePassword(text: String) { _textPassword.value = text }
}