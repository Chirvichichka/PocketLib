package com.chirvi.pocketlib.presentation.ui.screen.profile.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.GetSettingsUseCase
import com.chirvi.domain.usecase.SaveSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase
) : ViewModel() {

    private val _feedSwitchState = MutableLiveData(load())
    val feedSwitchState: LiveData<Boolean> = _feedSwitchState

    private val _myBooksCSwitchState = MutableLiveData(false)
    val myBooksCSwitchState: LiveData<Boolean> = _myBooksCSwitchState

    private val _favoriteSwitchState = MutableLiveData(false)
    val favoriteSwitchState: LiveData<Boolean> = _favoriteSwitchState
    //todo разобраться

    private fun load() : Boolean {
        val displayMode = getSettingsUseCase.executeFeed()
        Log.e("AAA", displayMode.name)
        return displayMode.name == DisplayMode.LIST.name
    }

    private fun save() {
        val displayMode = if(_feedSwitchState.value == true) {
            DisplayMode.GRID
        } else {
            DisplayMode.LIST
        }
        saveSettingsUseCase.executeFeed(displayMode)
    }

    fun feedSwitchStateChanged() {
        save()
        _feedSwitchState.value = !_feedSwitchState.value!!
    }
    fun myBooksSwitchStateChanged() { _myBooksCSwitchState.value = !_myBooksCSwitchState.value!! }
    fun favoriteSwitchStateChanged() { _favoriteSwitchState.value = !_favoriteSwitchState.value!! }
}