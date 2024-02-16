package com.chirvi.pocketlib.presentation.ui.screen.profile.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val _feedCSwitchState = MutableLiveData(false)
    val feedSwitchState: LiveData<Boolean> = _feedCSwitchState

    private val _myBooksCSwitchState = MutableLiveData(false)
    val myBooksCSwitchState: LiveData<Boolean> = _myBooksCSwitchState

    private val _favoriteSwitchState = MutableLiveData(false)
    val favoriteSwitchState: LiveData<Boolean> = _favoriteSwitchState
    fun feedSwitchStateChanged() {
        _feedCSwitchState.value = !_feedCSwitchState.value!! //todo разобраться
    }
    fun myBooksSwitchStateChanged() {
        _myBooksCSwitchState.value = !_myBooksCSwitchState.value!!
    }
    fun favoriteSwitchStateChanged() {
        _favoriteSwitchState.value = !_favoriteSwitchState.value!!
    }
}