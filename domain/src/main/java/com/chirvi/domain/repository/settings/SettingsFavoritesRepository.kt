package com.chirvi.domain.repository.settings

import com.chirvi.domain.models.DisplayMode

interface SettingsFavoritesRepository {
    fun saveFavoritesDisplayMode(mode: DisplayMode)
    fun getFavoritesDisplayMode(): DisplayMode
}