package com.chirvi.pocketlib.presentation.ui.screen.profile.settings.settings_display

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
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DisplayViewModel @Inject constructor(

    private val getSettingsFeedUseCase: GetSettingsFeedUseCase,
    private val saveSettingsFeedUseCase: SaveSettingsFeedUseCase,

    private val getSettingsFavoritesUseCase: GetSettingsFavoritesUseCase,
    private val saveSettingsFavoritesUseCase: SaveSettingsFavoritesUseCase,

    private val getSettingsMyBooksUseCase: GetSettingsMyBooksUseCase,
    private val saveSettingsMyBooksUseCase: SaveSettingsMyBooksUseCase,

) : ViewModel() {
    //todo разобраться с !!
    private val _feedSwitchState = MutableLiveData(loadFeedSettings())
    val feedSwitchState: LiveData<Boolean> = _feedSwitchState

    private val _myBooksCSwitchState = MutableLiveData(loadMyBooksSettings())
    val myBooksCSwitchState: LiveData<Boolean> = _myBooksCSwitchState

    private val _favoriteSwitchState = MutableLiveData(loadFavoritesSettings())
    val favoriteSwitchState: LiveData<Boolean> = _favoriteSwitchState

    private fun loadFeedSettings() : Boolean {
        val displayMode = getSettingsFeedUseCase()
        return displayMode.name == DisplayMode.LIST.name
    }
    private fun saveFeedSettings() {
        saveSettingsFeedUseCase(_feedSwitchState.value ?: false)
    }
    fun feedSwitchStateChanged() {
        saveFeedSettings()
        _feedSwitchState.value = !_feedSwitchState.value!!
    }


    private fun loadMyBooksSettings() : Boolean {
        val displayMode = getSettingsMyBooksUseCase()
        return displayMode.name == DisplayMode.LIST.name
    }
    private fun saveMyBooksSettings() {
        saveSettingsMyBooksUseCase(_myBooksCSwitchState.value ?: false)
    }
    fun myBooksSwitchStateChanged() {
        saveMyBooksSettings()
        _myBooksCSwitchState.value = !_myBooksCSwitchState.value!!
    }


    private fun loadFavoritesSettings() : Boolean {
        val displayMode = getSettingsFavoritesUseCase()
        return displayMode.name == DisplayMode.LIST.name
    }
    private fun saveFavoritesSettings() {
        saveSettingsFavoritesUseCase(_favoriteSwitchState.value ?: false)
    }
    fun favoriteSwitchStateChanged() {
        saveFavoritesSettings()
        _favoriteSwitchState.value = !_favoriteSwitchState.value!!
    }
}