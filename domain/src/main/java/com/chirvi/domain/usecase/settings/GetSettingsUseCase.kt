package com.chirvi.domain.usecase.settings

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsRepository

class GetSettingsUseCase(private val settingsRepository: SettingsRepository) {
    operator fun invoke(key: String) : DisplayMode {
        return settingsRepository.getDisplayMode(key = key)
    }
}