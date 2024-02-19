package com.chirvi.domain.usecase.settings

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsMyBooksRepository

class GetSettingsMyBooksUseCase(private val settingsRepository: SettingsMyBooksRepository) {
    operator fun invoke() : DisplayMode {
        return settingsRepository.getMyBooksDisplayMode()
    }
}