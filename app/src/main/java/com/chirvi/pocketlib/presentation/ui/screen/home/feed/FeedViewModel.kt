package com.chirvi.pocketlib.presentation.ui.screen.home.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.settings.GetSettingsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getSettingsFeedUseCase: GetSettingsFeedUseCase,
) : ViewModel()
{
    private val _newText = MutableLiveData("")
    val newText: LiveData<String> = _newText

    private val _feedDisplayMode = MutableLiveData(loadFeedSettings())
    val feedDisplayMode: LiveData<DisplayMode> = _feedDisplayMode
    private fun loadFeedSettings() : DisplayMode {
        val displayMode = getSettingsFeedUseCase()
        return displayMode
    }

    fun textChange(text: String) { _newText.value = text }
}