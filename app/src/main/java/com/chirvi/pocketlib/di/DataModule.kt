package com.chirvi.pocketlib.di

import android.content.Context
import com.chirvi.data.repository.BookReaderRepositoryImpl
import com.chirvi.data.repository.posts.PostsRepositoryImpl
import com.chirvi.data.repository.settings.SettingsRepositoryImpl
import com.chirvi.data.repository.storage.StorageRepositoryImpl
import com.chirvi.data.repository.users.UserRepositoryImpl
import com.chirvi.domain.repository.BookReaderRepository
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.settings.SettingsRepository
import com.chirvi.domain.repository.storage.StorageRepository
import com.chirvi.domain.repository.users.UserRepository
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
    fun provideBookReaderRepository(@ApplicationContext context: Context) : BookReaderRepository {
        return BookReaderRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUserRepository() : UserRepository {
        return UserRepositoryImpl()
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

}