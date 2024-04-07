package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.create_account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.ConfirmPasswordUseCase
import com.chirvi.domain.usecase.auth.RegistrationUseCase
import com.chirvi.pocketlib.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val confirmPasswordUseCase: ConfirmPasswordUseCase,
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textEMail = MutableLiveData("")
    val textEMail: LiveData<String> = _textEMail

    private val _textPassword = MutableLiveData("")
    val textPassword: LiveData<String> = _textPassword

    private val _textConfirmPassword = MutableLiveData("")
    val textConfirmPassword: LiveData<String> = _textConfirmPassword

    private val _errorMessageId = MutableLiveData(R.string.password_not_error)
    val errorMessageId: LiveData<Int> = _errorMessageId

    fun onValueChangeName(text: String) { _textName.value = text }

    fun onValueChangeEMail(text: String) { _textEMail.value = text }

    fun onValueChangePassword(text: String) { _textPassword.value = text }

    fun onValueChangeConfirmPassword(text: String) { _textConfirmPassword.value = text }
    private fun confirmPassword() {
        _errorMessage.value = confirmPasswordUseCase(
            password = _textPassword.value ?: "",
            passwordConfirm = _textConfirmPassword.value ?: ""
        )
    }
    fun registration() {
        confirmPassword()
        currentError(_errorMessage.value?: "")
        if (errorMessage.value == "") {
            viewModelScope.launch {
                registrationCoroutine()
            }
        }
    }
    private suspend fun registrationCoroutine() {
        _errorMessage.value = registrationUseCase(
            email = textEMail.value ?: "",
            password = textPassword.value ?: ""
        )
    }

    private fun currentError(error: String) {
        _errorMessageId.value =
            when(error) {
                "Short password length" -> {
                    R.string.password_short_length
                }
                "Password mismatch" -> {
                    R.string.password_mismatch
                }
                "Field is empty" -> {
                    R.string.password_is_empty
                }
                else -> {
                    R.string.password_not_error
                }
            }
    }
}