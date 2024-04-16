package com.chirvi.pocketlib.presentation.ui.screen.main

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.users.GetUserUseCase
import com.chirvi.pocketlib.presentation.models.UserPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import com.chirvi.pocketlib.presentation.ui.theme.ColorScheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _currentUser = MutableLiveData<UserPresentation?>(null)
    val currentUser: LiveData<UserPresentation?> = _currentUser

    private val _darkTheme = MutableLiveData(false)
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

    private suspend fun suspendGetUser() {
        val currentUserId = Firebase.auth.currentUser?.uid
        if(currentUserId != null) {
            viewModelScope.launch {
                val user = getUserUseCase(currentUserId).toPresentation()
                _currentUser.value = user
            }.join()
        }
    }



    fun getUser() {
        viewModelScope.launch { suspendGetUser() }
    }

    init {
        getUser()
    }
}