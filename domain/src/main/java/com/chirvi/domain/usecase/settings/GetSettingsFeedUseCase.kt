package com.chirvi.domain.usecase.settings

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsFeedRepository

class GetSettingsFeedUseCase(private val settingsRepository: SettingsFeedRepository) {
    operator fun invoke() : DisplayMode {
        return settingsRepository.getFeedDisplayMode()
    }
}