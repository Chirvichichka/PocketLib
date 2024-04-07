package com.chirvi.domain.usecase.settings

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsRepository

class SaveSettingsUseCase(private val settingsRepository: SettingsRepository) {
    operator fun invoke(key: String, state: Boolean) {
        val displayMode = if(state) {
            DisplayMode.GRID
        } else {
            DisplayMode.LIST
        }
        settingsRepository.saveDisplayMode(mode = displayMode, key = key)
    }
}