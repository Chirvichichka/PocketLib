package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.create_account

import android.net.Uri
import android.util.Log
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
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private val _state = MutableLiveData<CreateAccountState>(CreateAccountState.Initial)
    val state: LiveData<CreateAccountState> = _state

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textEMail = MutableLiveData("")
    val textEmail: LiveData<String> = _textEMail

    private val _textPassword = MutableLiveData("")
    val textPassword: LiveData<String> = _textPassword

    private val _image = MutableLiveData<Uri?>()
    val image: LiveData<Uri?> = _image

    fun onValueChangeName(text: String) { _textName.value = text }

    fun onValueChangeEMail(text: String) { _textEMail.value = text }

    fun onValueChangePassword(text: String) { _textPassword.value = text }
    fun deleteImage() { _image.value = null }
    fun changeImage(imageUri: Uri) { _image.value = imageUri }
    fun navigateToProfileScreen(navigationState: NavigationState) {
        navigationState.navigateTo(Screen.Profile.route)
    }

    fun registration() {
        Log.e("AAA", errorMessage.value.toString())
        if (errorMessage.value == "") {
            viewModelScope.launch {
                suspendRegistration() //todo ередеалть
            }
        }
    }

    private suspend fun suspendRegistration() {
        _state.value = CreateAccountState.Loading
        viewModelScope.launch {
            val user = UserPresentation(
                username = textName.value?:"",
                email = textEmail.value?:"",
                password = textPassword.value?:"",
                avatar = image.value,
            ).toDomain()
            _errorMessage.value = registrationUseCase(user = user)
            if (errorMessage.value == "") {
                saveUserUseCase(user)
                _state.value = CreateAccountState.Complete
            } else {
                _state.value = CreateAccountState.Initial
            }
        }.join()
    }

    fun navigateToBack(navigation: NavigationState) {
        navigation.navHostController.popBackStack()
    }
}