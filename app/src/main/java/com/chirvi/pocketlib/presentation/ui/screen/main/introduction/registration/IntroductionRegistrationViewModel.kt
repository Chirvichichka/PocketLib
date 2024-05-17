package com.chirvi.pocketlib.presentation.ui.screen.main.introduction.registration

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.domain.usecase.users.RegistrationUseCase
import com.chirvi.domain.usecase.users.SaveUserUseCase
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionRegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private val _state = MutableLiveData<IntroductionRegistrationState>(IntroductionRegistrationState.Initial)
    val state: LiveData<IntroductionRegistrationState> = _state

    private val _isErrorName = MutableLiveData(false)
    val isErrorName: LiveData<Boolean> = _isErrorName

    private val _isErrorEmail = MutableLiveData(false)
    val isErrorEmail: LiveData<Boolean> = _isErrorEmail

    private val _isErrorPassword = MutableLiveData(false)
    val isErrorPassword: LiveData<Boolean> = _isErrorPassword

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textEmail = MutableLiveData("")
    val textEmail: LiveData<String> = _textEmail

    private val _textPassword = MutableLiveData("")
    val textPassword: LiveData<String> = _textPassword

    private val _image = MutableLiveData<Uri?>()
    val image: LiveData<Uri?> = _image

    fun onValueChangeName(text: String) { _textName.value = text }

    fun onValueChangeEMail(text: String) { _textEmail.value = text }

    fun onValueChangePassword(text: String) { _textPassword.value = text }

    fun deleteImage() { _image.value = null }

    fun changeImage(imageUri: Uri) { _image.value = imageUri }

    fun navigateToProfile(navigationState: NavigationState) { navigationState.navigateTo(Screen.Profile.route) }

    fun registration() {
        checkField()
//        _state.value = IntroductionRegistrationState.Loading
//        viewModelScope.launch {
//            val user = UserPresentation(
//                username = textName.value?:"",
//                email = textEmail.value?:"",
//                password = textPassword.value?:"",
//                avatar = image.value,
//            ).toDomain()
//            _errorMessage.value = registrationUseCase(user = user)
//            if (_errorMessage.value == null) {
//                saveUserUseCase(user)
//                _state.value = IntroductionRegistrationState.Complete
//            } else {
//                _state.value = IntroductionRegistrationState.Initial
//            }
//        }
    }
    fun checkField() {
        if(_textName.value == "") {
            _isErrorName.value = !isErrorName.value!!
        }
        if(_textEmail.value == "") {
            _isErrorEmail.value = !_isErrorEmail.value!!
        }
        if(_textPassword.value == "") {
            _isErrorPassword.value = !_isErrorPassword.value!!
        }
    }

}