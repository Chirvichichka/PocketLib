package com.chirvi.pocketlib.di

import com.chirvi.domain.repository.BookReaderRepository
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.settings.SettingsRepository
import com.chirvi.domain.repository.storage.StorageRepository
import com.chirvi.domain.repository.users.UserRepository
import com.chirvi.domain.usecase.BookReaderUseCase
import com.chirvi.domain.usecase.ConfirmPasswordUseCase
import com.chirvi.domain.usecase.posts.CreateIdUseCase
import com.chirvi.domain.usecase.posts.GetAllBooksUseCase
import com.chirvi.domain.usecase.posts.GetBookByIdUseCase
import com.chirvi.domain.usecase.posts.GetUserBooksUseCase
import com.chirvi.domain.usecase.posts.LoadImageUseCase
import com.chirvi.domain.usecase.posts.SaveBookUseCase
import com.chirvi.domain.usecase.posts.SaveImageUseCase
import com.chirvi.domain.usecase.settings.GetSettingsUseCase
import com.chirvi.domain.usecase.settings.SaveSettingsUseCase
import com.chirvi.domain.usecase.users.AuthenticationUseCase
import com.chirvi.domain.usecase.users.GetUserUseCase
import com.chirvi.domain.usecase.users.RegistrationUseCase
import com.chirvi.domain.usecase.users.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideBookReaderUseCase(bookReaderRepository: BookReaderRepository) : BookReaderUseCase {
        return BookReaderUseCase(bookReaderRepository = bookReaderRepository)
    }

    @Provides
    fun provideGetUserBooksUseCase(postsRepository: PostsRepository, storage: StorageRepository) : GetUserBooksUseCase {
        return GetUserBooksUseCase(postsRepository = postsRepository, storageRepository = storage)
    }

    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository) : GetUserUseCase {
        return GetUserUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideSaveUserUseCase(userRepository: UserRepository, storage: StorageRepository) : SaveUserUseCase {
        return SaveUserUseCase(userRepository = userRepository, storage = storage)
    }

    @Provides
    fun providesAuthenticationUseCase(userRepository: UserRepository) : AuthenticationUseCase {
        return AuthenticationUseCase(userRepository = userRepository)
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
    fun providesRegistrationUseCase(userRepository: UserRepository) : RegistrationUseCase {
        return RegistrationUseCase(userRepository = userRepository)
    }

}