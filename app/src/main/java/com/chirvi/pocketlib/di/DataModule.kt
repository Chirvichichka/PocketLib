package com.chirvi.pocketlib.di

import android.content.Context
import com.chirvi.data.repository.SettingsRepositoryImpl
import com.chirvi.domain.repository.SettingsRepository
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
    fun provideSettingsRepository(@ApplicationContext context: Context) : SettingsRepository {
        return SettingsRepositoryImpl(context = context)
    }
}