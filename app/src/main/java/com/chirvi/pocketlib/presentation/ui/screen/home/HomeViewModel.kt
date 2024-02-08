package com.chirvi.pocketlib.presentation.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(){

    private val _isGrid = MutableLiveData(false)
    val isGrid: LiveData<Boolean> = _isGrid

    private val _newText = MutableLiveData("")
    val newText: LiveData<String> = _newText

    fun textChange(
        text: String
    ) {
        _newText.value = text
    }

    fun gridChange() {
        _isGrid.value = !_isGrid.value!!
    }
}