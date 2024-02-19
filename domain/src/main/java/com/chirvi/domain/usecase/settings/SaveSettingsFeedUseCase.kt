package com.chirvi.domain.usecase.settings

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsFeedRepository

class SaveSettingsFeedUseCase(private val settingsRepository: SettingsFeedRepository) {
    operator fun invoke(state: Boolean) {
        val displayMode = if(state) {
            DisplayMode.GRID
        } else {
            DisplayMode.LIST
        }
        settingsRepository.saveFeedDisplayMode(displayMode)
    }
}