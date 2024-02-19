package com.chirvi.pocketlib.di

import android.content.Context
import com.chirvi.data.repository.settings.SettingsFavoritesRepositoryImpl
import com.chirvi.data.repository.settings.SettingsFeedRepositoryImpl
import com.chirvi.data.repository.settings.SettingsMyBooksRepositoryImpl
import com.chirvi.domain.repository.settings.SettingsFavoritesRepository
import com.chirvi.domain.repository.settings.SettingsFeedRepository
import com.chirvi.domain.repository.settings.SettingsMyBooksRepository
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
    fun provideSettingsFeedRepository(@ApplicationContext context: Context) : SettingsFeedRepository {
        return SettingsFeedRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideSettingsFavoritesRepository(@ApplicationContext context: Context) : SettingsFavoritesRepository {
        return SettingsFavoritesRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideSettingsMyBooksRepository(@ApplicationContext context: Context) : SettingsMyBooksRepository {
        return SettingsMyBooksRepositoryImpl(context = context)
    }
}