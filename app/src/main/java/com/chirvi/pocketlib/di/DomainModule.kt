package com.chirvi.pocketlib.di

import com.chirvi.domain.repository.auth.AuthenticationRepository
import com.chirvi.domain.repository.auth.RegistrationRepository
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.settings.SettingsRepository
import com.chirvi.domain.repository.storage.StorageRepository
import com.chirvi.domain.usecase.auth.AuthenticationUseCase
import com.chirvi.domain.usecase.auth.ConfirmPasswordUseCase
import com.chirvi.domain.usecase.auth.RegistrationUseCase
import com.chirvi.domain.usecase.posts.CreateIdUseCase
import com.chirvi.domain.usecase.posts.GetAllBooksUseCase
import com.chirvi.domain.usecase.posts.GetBookByIdUseCase
import com.chirvi.domain.usecase.posts.LoadImageUseCase
import com.chirvi.domain.usecase.posts.SaveBookUseCase
import com.chirvi.domain.usecase.posts.SaveImageUseCase
import com.chirvi.domain.usecase.settings.GetSettingsUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providesAuthenticationUseCase(authenticationRepository: AuthenticationRepository) : AuthenticationUseCase {
        return AuthenticationUseCase(authenticationRepository = authenticationRepository)
    }

    @Provides
    fun provideGetSettingsUseCase(settingsRepository: SettingsRepository) : GetSettingsUseCase {
        return GetSettingsUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideSaveSettingsUseCase(settingsRepository : SettingsRepository) : SaveSettingsUseCase {
        return SaveSettingsUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideCreateIdUseCase(repository: PostsRepository) : CreateIdUseCase {
        return CreateIdUseCase(repository = repository)
    }

    @Provides
    fun provideSaveBookUseCase(repository: PostsRepository, storage: StorageRepository) : SaveBookUseCase {
        return SaveBookUseCase(repository = repository, storage = storage)
    }

    @Provides
    fun provideGetAllBooksUseCase(repository: PostsRepository, storage: StorageRepository) : GetAllBooksUseCase {
        return GetAllBooksUseCase(repository = repository, storageRepository = storage)
    }

    @Provides
    fun provideGetBookByIdUseCase(repository: PostsRepository, storage: StorageRepository) : GetBookByIdUseCase {
        return GetBookByIdUseCase(repository = repository, storageRepository = storage)
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
    fun provideConfirmPasswordUseCase() : ConfirmPasswordUseCase {
        return ConfirmPasswordUseCase()
    }
    @Provides
    fun providesRegistrationUseCase(repository: RegistrationRepository) : RegistrationUseCase {
        return RegistrationUseCase(repository = repository)
    }

}