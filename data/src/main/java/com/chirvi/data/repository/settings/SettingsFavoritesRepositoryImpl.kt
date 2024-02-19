package com.chirvi.data.repository.settings

import android.content.Context
import android.content.SharedPreferences
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsFavoritesRepository

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FAVORITES = "favorites"

class SettingsFavoritesRepositoryImpl(context: Context) : SettingsFavoritesRepository {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveFavoritesDisplayMode(mode: DisplayMode) {
        sharedPreferences.edit().putString(KEY_FAVORITES, mode.name).apply()
    }
    override fun getFavoritesDisplayMode(): DisplayMode {
        val modeString = sharedPreferences.getString(KEY_FAVORITES, null)
        return modeString?.let { DisplayMode.valueOf(it) } ?: DisplayMode.LIST
    }
}