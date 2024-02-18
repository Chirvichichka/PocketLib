package com.chirvi.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.SettingsRepository

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FEED = "feed"
private const val KEY_MY_BOOKS = "my_books"
private const val KEY_FAVORITES = "favorites"

class SettingsRepositoryImpl(context: Context) : SettingsRepository {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveFeedDisplayMode(mode: DisplayMode) {
        sharedPreferences.edit().putString(KEY_FEED, mode.name).apply()
    }
    override fun getFeedDisplayMode(): DisplayMode {
        val modeString = sharedPreferences.getString(KEY_FEED, null)
        return modeString?.let { DisplayMode.valueOf(it) } ?: DisplayMode.LIST
    }


    override fun saveMyBooksDisplayMode(mode: DisplayMode) {
        sharedPreferences.edit().putString(KEY_MY_BOOKS, mode.name).apply()
    }
    override fun getMyBooksDisplayMode(): DisplayMode {
        val modeString = sharedPreferences.getString(KEY_MY_BOOKS, null)
        return modeString?.let { DisplayMode.valueOf(it) } ?: DisplayMode.LIST
    }


    override fun saveFavoritesDisplayMode(mode: DisplayMode) {
        sharedPreferences.edit().putString(KEY_FAVORITES, mode.name).apply()
    }
    override fun getFavoritesDisplayMode(): DisplayMode {
        val modeString = sharedPreferences.getString(KEY_FAVORITES, null)
        return modeString?.let { DisplayMode.valueOf(it) } ?: DisplayMode.LIST
    }
}
