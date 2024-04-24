package com.chirvi.pocketlib.presentation.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.users.GetUserUseCase
import com.chirvi.pocketlib.presentation.models.UserPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.ui.theme.ColorScheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _currentUser = MutableLiveData<UserPresentation?>()
    val currentUser: LiveData<UserPresentation?> = _currentUser

    private val _startDestination = MutableLiveData(Screen.Home.route)
    val startDestination: LiveData<String> = _startDestination

    private val _darkTheme = MutableLiveData<Boolean>()
    val darkTheme: LiveData<Boolean> = _darkTheme

    private val _colorScheme = MutableLiveData(ColorScheme.BLUE)
    val colorScheme: LiveData<ColorScheme> = _colorScheme

    fun changeColorScheme(newColorScheme: ColorScheme) {
        _colorScheme.value = newColorScheme
    }

    fun changeTheme() {
        val currentTheme = darkTheme.value?:false
        _darkTheme.value = !currentTheme
    }

    fun getUser() {
        val currentUserId = Firebase.auth.currentUser?.uid
        if(currentUserId != null) {
            viewModelScope.launch {
                val user = getUserUseCase(currentUserId).toPresentation()
                _currentUser.value = user
                _startDestination.value = Screen.Home.route
            }
        } else {
            _startDestination.value = Screen.Introduction.route
            _currentUser.value = null
        }
    }
}