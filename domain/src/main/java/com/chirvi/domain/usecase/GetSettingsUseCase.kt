package com.chirvi.domain.usecase

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.SettingsRepository

class GetSettingsUseCase(private val settingsRepository: SettingsRepository) {

    fun executeFeed() : DisplayMode {
        return settingsRepository.getFeedDisplayMode()
    }
    fun executeMyBooks(): DisplayMode {
        return settingsRepository.getMyBooksDisplayMode()
    }
    fun executeFavorites(): DisplayMode {
        return settingsRepository.getFavoritesDisplayMode()
    }
}