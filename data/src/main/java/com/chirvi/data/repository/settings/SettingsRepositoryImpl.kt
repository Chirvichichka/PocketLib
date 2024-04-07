package com.chirvi.data.repository.settings

import android.content.Context
import android.content.SharedPreferences
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsRepository

private const val SHARED_PREFS_NAME = "shared_prefs_name"

class SettingsRepositoryImpl(context: Context) : SettingsRepository {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveDisplayMode(mode: DisplayMode, key: String) {
        sharedPreferences.edit().putString(key, mode.name).apply()
    }
    override fun getDisplayMode(key: String): DisplayMode {
        val modeString = sharedPreferences.getString(key, null)
        return modeString?.let { DisplayMode.valueOf(it) } ?: DisplayMode.LIST
    }
}