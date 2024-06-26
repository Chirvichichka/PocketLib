package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.settings.GetSettingsUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsUseCase
import com.chirvi.pocketlib.presentation.constants.DisplayModeKeys
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase,
) : ViewModel() {
    private val _isDarkTheme = MutableLiveData(false)
    val isDarkTheme: LiveData<Boolean> = _isDarkTheme

    private val _feedSwitchState = MutableLiveData(getDisplay(key = DisplayModeKeys.FEED_KEY))
    val feedSwitchState: LiveData<Boolean> = _feedSwitchState

    private val _myBooksSwitchState = MutableLiveData(getDisplay(key = DisplayModeKeys.MY_BOOKS_KEY))
    val myBooksSwitchState: LiveData<Boolean> = _myBooksSwitchState

    private val _favoriteSwitchState = MutableLiveData(getDisplay(key = DisplayModeKeys.FAVORITES_KEY))
    val favoriteSwitchState: LiveData<Boolean> = _favoriteSwitchState

    init {
        getDisplay(key = DisplayModeKeys.FEED_KEY)
        getDisplay(key = DisplayModeKeys.MY_BOOKS_KEY)
        getDisplay(key = DisplayModeKeys.FAVORITES_KEY)
    }

    fun themeModChange() {
        val currentThemeMode = _isDarkTheme.value ?: false
        _isDarkTheme.value = !currentThemeMode
    }

    fun feedChange() {
        saveSettingsUseCase(key = DisplayModeKeys.FEED_KEY, state =  _feedSwitchState.value!!)
        _feedSwitchState.value = !_feedSwitchState.value!!
    }

    fun myBooksChange() {
        saveSettingsUseCase(key = DisplayModeKeys.MY_BOOKS_KEY, state =  _myBooksSwitchState.value!!)
        _myBooksSwitchState.value = !_myBooksSwitchState.value!!
    }

    fun favoritesChange() {
        saveSettingsUseCase(key = DisplayModeKeys.FAVORITES_KEY, state =  _favoriteSwitchState.value!!)
        _favoriteSwitchState.value = !_favoriteSwitchState.value!!
    }

    fun navigateToBack(navigation: NavigationState) {
        navigation.navHostController.popBackStack()
    }

    fun navigateToRegistration(navigation: NavigationState) {
        navigation.navigateTo(Screen.Registration.route)
    }

    fun navigateToLogin(navigation: NavigationState) {
        navigation.navigateTo(Screen.Login.route)
    }

    private fun getDisplay(key: String) : Boolean{
        val currentState = getSettingsUseCase(key).name == DisplayMode.LIST.name
        return currentState
    }
}