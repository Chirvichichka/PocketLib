package com.chirvi.pocketlib.presentation.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.pocketlib.presentation.navigation.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(){

    private val initialState = HomeScreenState.Feed

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: MutableLiveData<HomeScreenState> = _screenState

    private var savedState: HomeScreenState? = initialState

    fun showPost() {
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Post
    }

    fun closePost() {
        _screenState.value = savedState ?: HomeScreenState.Initial
    }

}