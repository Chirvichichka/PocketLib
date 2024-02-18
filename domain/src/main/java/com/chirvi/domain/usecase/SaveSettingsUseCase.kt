package com.chirvi.domain.usecase

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.SettingsRepository

class SaveSettingsUseCase(private val settingsRepository: SettingsRepository) {
    fun executeFeed(settings: DisplayMode) {
        settingsRepository.saveFeedDisplayMode(settings)
    }
    fun executeMyBooks(settings: DisplayMode) {
        settingsRepository.saveMyBooksDisplayMode(settings)
    }

    fun executeFavorites(settings: DisplayMode) {
        settingsRepository.saveFavoritesDisplayMode(settings)
    }
}