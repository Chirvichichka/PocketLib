package com.chirvi.pocketlib.di

import com.chirvi.domain.repository.SettingsRepository
import com.chirvi.domain.usecase.GetSettingsUseCase
import com.chirvi.domain.usecase.SaveSettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetSettingsUseCase(settingsRepository : SettingsRepository) : GetSettingsUseCase {
        return GetSettingsUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideSaveSettingsUseCase(settingsRepository : SettingsRepository) : SaveSettingsUseCase {
        return SaveSettingsUseCase(settingsRepository = settingsRepository)
    }
}