package com.chirvi.pocketlib.presentation.ui.screen.profile.settings.create_account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.domain.usecase.ConfirmPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val confirmPasswordUseCase: ConfirmPasswordUseCase
) : ViewModel() {

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textEMail = MutableLiveData("")
    val textEMail: LiveData<String> = _textEMail

    private val _textPassword = MutableLiveData("")
    val textPassword: LiveData<String> = _textPassword

    private val _textConfirmPassword = MutableLiveData("")
    val textConfirmPassword: LiveData<String> = _textConfirmPassword

    private val _isPasswordConfirm = MutableLiveData(false)
    val isPasswordConfirm: LiveData<Boolean> = _isPasswordConfirm

    fun onValueChangeName(text: String) { _textName.value = text }

    fun onValueChangeEMail(text: String) { _textEMail.value = text }

    fun onValueChangePassword(text: String) { _textPassword.value = text }

    fun onValueChangeConfirmPassword(text: String) { _textConfirmPassword.value = text }
    fun confirmPassword() {
        _isPasswordConfirm.value = confirmPasswordUseCase(
            password = _textPassword.value ?: "",
            passwordConfirm = _textConfirmPassword.value ?: ""
        )
    }
}