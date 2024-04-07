package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.usecase.settings.GetSettingsUseCase
import com.chirvi.pocketlib.presentation.constants.DisplayModeKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase

) : ViewModel() {
    private val _image = MutableLiveData(Uri.EMPTY)
    val image: LiveData<Uri?> = _image

    private val _tabRowIndex = MutableLiveData(0)
    val tabRowItem: LiveData<Int> = _tabRowIndex

    fun onIndexChange(index: Int) { _tabRowIndex.value = index }

    private val _myBooksDisplayMode = MutableLiveData(loadMyBooksSettings())
    val myBooksDisplayMode: LiveData<DisplayMode> = _myBooksDisplayMode
    private fun loadMyBooksSettings() : DisplayMode {
        val displayMode = getSettingsUseCase(DisplayModeKeys.MY_BOOKS_KEY)
        return displayMode
    }

    private val _favoritesDisplayMode = MutableLiveData(loadFavoritesSettings())
    val favoritesDisplayMode: LiveData<DisplayMode> = _favoritesDisplayMode
    private fun loadFavoritesSettings() : DisplayMode {
        val displayMode = getSettingsUseCase(DisplayModeKeys.FAVORITES_KEY)
        return displayMode
    }
}