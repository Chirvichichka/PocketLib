package com.chirvi.pocketlib.presentation.ui.screen.profile.settings

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.settings.GetSettingsFavoritesUseCase
import com.chirvi.domain.usecase.settings.GetSettingsFeedUseCase
import com.chirvi.domain.usecase.settings.GetSettingsMyBooksUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsFavoritesUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsFeedUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsMyBooksUseCase
import com.chirvi.pocketlib.presentation.ui.screen.home.feed.FeedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : ViewModel() {


}