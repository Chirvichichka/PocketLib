package com.chirvi.domain.usecase.settings

import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsMyBooksRepository

class SaveSettingsMyBooksUseCase(private val settingsRepository: SettingsMyBooksRepository) {
    operator fun invoke(state: Boolean) {
        val displayMode = if(state) {
            DisplayMode.GRID
        } else {
            DisplayMode.LIST
        }
        settingsRepository.saveMyBooksDisplayMode(displayMode)
    }
}