package com.chirvi.domain.usecase.settings

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsFavoritesRepository

class GetSettingsFavoritesUseCase(private val settingsRepository: SettingsFavoritesRepository) {
    operator fun invoke() : DisplayMode {
        return settingsRepository.getFavoritesDisplayMode()
    }
}