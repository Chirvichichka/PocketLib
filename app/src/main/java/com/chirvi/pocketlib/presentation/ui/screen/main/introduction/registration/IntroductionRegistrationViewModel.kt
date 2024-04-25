package com.chirvi.pocketlib.presentation.ui.screen.main.introduction.registration

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.ConfirmPasswordUseCase
import com.chirvi.domain.usecase.users.RegistrationUseCase
import com.chirvi.domain.usecase.users.SaveUserUseCase
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.models.UserPresentation
import com.chirvi.pocketlib.presentation.models.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroductionRegistrationViewModel @Inject constructor(
    private val confirmPasswordUseCase: ConfirmPasswordUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private val _state = MutableLiveData<IntroductionRegistrationState>(IntroductionRegistrationState.Initial)
    val state: LiveData<IntroductionRegistrationState> = _state

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textEMail = MutableLiveData("")
    val textEmail: LiveData<String> = _textEMail

    private val _textPassword = MutableLiveData("")
    val textPassword: LiveData<String> = _textPassword

    private val _textConfirmPassword = MutableLiveData("")
    val textConfirmPassword: LiveData<String> = _textConfirmPassword

    private val _image = MutableLiveData<Uri?>()
    val image: LiveData<Uri?> = _image

    fun onValueChangeName(text: String) { _textName.value = text }

    fun onValueChangeEMail(text: String) { _textEMail.value = text }

    fun onValueChangePassword(text: String) { _textPassword.value = text }

    fun onValueChangeConfirmPassword(text: String) { _textConfirmPassword.value = text }

    fun deleteImage() { _image.value = null }
    fun changeImage(imageUri: Uri) { _image.value = imageUri }

    private fun confirmPassword() {
        _errorMessage.value = confirmPasswordUseCase(
            password = _textPassword.value ?: "",
            passwordConfirm = _textConfirmPassword.value ?: ""
        )
    }

    fun registration() {
        confirmPassword()
        _state.value = IntroductionRegistrationState.Loading
        if (errorMessage.value == "") {
            viewModelScope.launch {
                val user = UserPresentation(
                    username = textName.value?:"",
                    email = textEmail.value?:"",
                    password = textConfirmPassword.value?:"",
                    avatar = image.value,
                ).toDomain()
                _errorMessage.value = registrationUseCase(user = user)
                saveUserUseCase(user)
                _state.value = IntroductionRegistrationState.Complete
            }
        }
    }
}