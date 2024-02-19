package com.chirvi.pocketlib.di

import com.chirvi.domain.repository.settings.SettingsFavoritesRepository
import com.chirvi.domain.repository.settings.SettingsFeedRepository
import com.chirvi.domain.repository.settings.SettingsMyBooksRepository
import com.chirvi.domain.usecase.settings.GetSettingsFavoritesUseCase
import com.chirvi.domain.usecase.settings.GetSettingsFeedUseCase
import com.chirvi.domain.usecase.settings.GetSettingsMyBooksUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsFavoritesUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsFeedUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsMyBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetSettingsFeedUseCase(settingsRepository: SettingsFeedRepository) : GetSettingsFeedUseCase {
        return GetSettingsFeedUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideGetSettingsFavoritesUseCase(settingsRepository: SettingsFavoritesRepository) : GetSettingsFavoritesUseCase {
        return GetSettingsFavoritesUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideGetSettingsMyBooksUseCase(settingsRepository: SettingsMyBooksRepository) : GetSettingsMyBooksUseCase {
        return GetSettingsMyBooksUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideSaveSettingsFeedUseCase(settingsRepository : SettingsFeedRepository) : SaveSettingsFeedUseCase {
        return SaveSettingsFeedUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideSaveSettingsFavoritesUseCase(settingsRepository : SettingsFavoritesRepository) : SaveSettingsFavoritesUseCase {
        return SaveSettingsFavoritesUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideSaveSettingsMyBooksUseCase(settingsRepository : SettingsMyBooksRepository) : SaveSettingsMyBooksUseCase {
        return SaveSettingsMyBooksUseCase(settingsRepository = settingsRepository)
    }
}