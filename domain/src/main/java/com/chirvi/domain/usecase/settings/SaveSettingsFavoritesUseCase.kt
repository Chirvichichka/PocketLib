package com.chirvi.domain.usecase.settings

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsFavoritesRepository

class SaveSettingsFavoritesUseCase(private val settingsRepository: SettingsFavoritesRepository) {
    operator fun invoke(state: Boolean) {
        val displayMode = if(state) {
            DisplayMode.GRID
        } else {
            DisplayMode.LIST
        }
        settingsRepository.saveFavoritesDisplayMode(displayMode)
    }
}