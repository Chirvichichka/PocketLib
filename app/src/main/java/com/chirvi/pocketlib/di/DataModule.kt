package com.chirvi.pocketlib.di

import android.content.Context
import com.chirvi.data.repository.auth.AuthenticationRepositoryImpl
import com.chirvi.data.repository.auth.RegistrationRepositoryImpl
import com.chirvi.data.repository.posts.PostsRepositoryImpl
import com.chirvi.data.repository.settings.SettingsRepositoryImpl
import com.chirvi.data.repository.storage.StorageRepositoryImpl
import com.chirvi.domain.repository.auth.AuthenticationRepository
import com.chirvi.domain.repository.auth.RegistrationRepository
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.settings.SettingsRepository
import com.chirvi.domain.repository.storage.StorageRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository() : AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(@ApplicationContext context: Context) : SettingsRepository {
        return SettingsRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideStorageRepository() : StorageRepository {
        return StorageRepositoryImpl()
    }

    @Provides
    fun providePostsRepository() : PostsRepository {
        return PostsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providesRegistrationRepository() : RegistrationRepository {
        return RegistrationRepositoryImpl()
    }

}