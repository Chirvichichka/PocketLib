package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.storage.StorageRepository
import com.chirvi.domain.usecase.posts.LoadImageUseCase
import com.chirvi.domain.usecase.settings.GetSettingsFavoritesUseCase
import com.chirvi.domain.usecase.settings.GetSettingsMyBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSettingsMyBooksUseCase: GetSettingsMyBooksUseCase,
    private val getSettingsFavoritesUseCase: GetSettingsFavoritesUseCase,
    private val loadImageUseCase: LoadImageUseCase,
    private val storageRepository: StorageRepository,

    ) : ViewModel() {
    private val _image = MutableLiveData(Uri.EMPTY)
    val image: LiveData<Uri?> = _image
    init {
        viewModelScope.launch {
            _image.value = storageRepository.loadImage("-NtFGWnCeoGPgPG8BK2Y").toUri()
        }
    }

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