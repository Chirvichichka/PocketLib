package com.chirvi.pocketlib.presentation.ui.screen.profile.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val _feedCheckBoxState = MutableLiveData(false)
    val feedCheckBoxState: LiveData<Boolean> = _feedCheckBoxState

    fun feedCheckBoxStateChanged() {
        _feedCheckBoxState.value = !_feedCheckBoxState.value!!
    }
}