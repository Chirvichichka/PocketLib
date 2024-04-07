package com.chirvi.domain.repository.settings

import com.chirvi.domain.models.DisplayMode

interface SettingsRepository {
    fun saveDisplayMode(mode: DisplayMode, key: String)
    fun getDisplayMode(key: String): DisplayMode
}