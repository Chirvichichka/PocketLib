package com.chirvi.pocketlib.di

import com.chirvi.domain.repository.auth.RegistrationRepository
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.settings.SettingsFavoritesRepository
import com.chirvi.domain.repository.settings.SettingsFeedRepository
import com.chirvi.domain.repository.settings.SettingsMyBooksRepository
import com.chirvi.domain.repository.storage.StorageRepository
import com.chirvi.domain.usecase.ConfirmPasswordUseCase
import com.chirvi.domain.usecase.auth.RegistrationUseCase
import com.chirvi.domain.usecase.posts.AddPostUseCase
import com.chirvi.domain.usecase.posts.GetUrlUseCase
import com.chirvi.domain.usecase.posts.LoadBookByIdUseCase
import com.chirvi.domain.usecase.posts.LoadImageUseCase
import com.chirvi.domain.usecase.posts.SaveImageUseCase
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
    fun provideLoadBookByIdUseCase(repository: PostsRepository) : LoadBookByIdUseCase {
        return LoadBookByIdUseCase(repository = repository)
    }
    @Provides
    fun provideLoadImageUseCase(storage: StorageRepository) : LoadImageUseCase {
        return LoadImageUseCase(storage = storage)
    }

    @Provides
    fun provideSaveImageUseCase(storage: StorageRepository) : SaveImageUseCase {
        return SaveImageUseCase(storage = storage)
    }

    @Provides
    fun provideGetUrlUseCAse(storage: StorageRepository) : GetUrlUseCase {
        return GetUrlUseCase(storage = storage)
    }

    @Provides
    fun provideConfirmPasswordUseCase() : ConfirmPasswordUseCase {
        return ConfirmPasswordUseCase()
    }
    @Provides
    fun provideAddPostUseCase(repository: PostsRepository) : AddPostUseCase {
        return AddPostUseCase(repository = repository)
    }

    @Provides
    fun providesRegistrationUseCase(repository: RegistrationRepository) : RegistrationUseCase {
        return RegistrationUseCase(repository = repository)
    }

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