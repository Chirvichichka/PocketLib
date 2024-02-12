package com.chirvi.pocketlib.presentation.ui.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.pocketlib.presentation.navigation.state.FeedScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(){

    private val initialState = FeedScreenState.Feed

    private val _screenState = MutableLiveData<FeedScreenState>(initialState)
    val screenState: MutableLiveData<FeedScreenState> = _screenState

    private var savedState: FeedScreenState? = initialState

}