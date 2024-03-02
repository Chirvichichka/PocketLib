package com.chirvi.pocketlib.presentation.ui.screen.profile.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.settings.GetSettingsFavoritesUseCase
import com.chirvi.domain.usecase.settings.GetSettingsMyBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSettingsMyBooksUseCase: GetSettingsMyBooksUseCase,
    private val getSettingsFavoritesUseCase: GetSettingsFavoritesUseCase,

    ) : ViewModel() {

    private val _tabRowIndex = MutableLiveData(0)
    val tabRowItem: LiveData<Int> = _tabRowIndex

    fun onIndexChange(index: Int) { _tabRowIndex.value = index }

    private val _myBooksDisplayMode = MutableLiveData(loadMyBooksSettings())
    val myBooksDisplayMode: LiveData<DisplayMode> = _myBooksDisplayMode
    private fun loadMyBooksSettings() : DisplayMode {
        val displayMode = getSettingsMyBooksUseCase()
        return displayMode
    }

    private val _favoritesDisplayMode = MutableLiveData(loadFavoritesSettings())
    val favoritesDisplayMode: LiveData<DisplayMode> = _favoritesDisplayMode
    private fun loadFavoritesSettings() : DisplayMode {
        val displayMode = getSettingsFavoritesUseCase()
        return displayMode
    }

}