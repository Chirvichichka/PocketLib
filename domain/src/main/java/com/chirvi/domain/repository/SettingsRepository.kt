package com.chirvi.domain.repository

import com.chirvi.domain.models.DisplayMode

interface SettingsRepository {
    fun saveFeedDisplayMode(mode: DisplayMode)
    fun getFeedDisplayMode(): DisplayMode

    fun saveMyBooksDisplayMode(mode: DisplayMode)
    fun getMyBooksDisplayMode(): DisplayMode

    fun saveFavoritesDisplayMode(mode: DisplayMode)
    fun getFavoritesDisplayMode(): DisplayMode
}