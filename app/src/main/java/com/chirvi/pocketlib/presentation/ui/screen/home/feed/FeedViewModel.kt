package com.chirvi.pocketlib.presentation.ui.screen.home.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.pocketlib.presentation.navigation.state.FeedScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor() : ViewModel() {

    private val initialState = FeedScreenState.Feed

    private val _screenState = MutableLiveData<FeedScreenState>(initialState)
    val screenState: MutableLiveData<FeedScreenState> = _screenState

    private var savedState: FeedScreenState? = initialState





    private val _isGrid = MutableLiveData(false) //todo переименовать
    val isGrid: LiveData<Boolean> = _isGrid

    private val _newText = MutableLiveData("")
    val newText: LiveData<String> = _newText

    fun textChange(text: String) { _newText.value = text }

    fun gridChange() { _isGrid.value = !_isGrid.value!! }
}