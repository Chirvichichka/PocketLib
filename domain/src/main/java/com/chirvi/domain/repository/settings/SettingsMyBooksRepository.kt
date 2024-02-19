package com.chirvi.domain.repository.settings

import com.chirvi.domain.models.DisplayMode

interface SettingsMyBooksRepository {
    fun saveMyBooksDisplayMode(mode: DisplayMode)
    fun getMyBooksDisplayMode(): DisplayMode
}