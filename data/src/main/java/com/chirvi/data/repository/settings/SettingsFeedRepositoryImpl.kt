package com.chirvi.data.repository.settings

import android.content.Context
import android.content.SharedPreferences
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsFeedRepository

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FEED = "feed"

class SettingsFeedRepositoryImpl(context: Context) : SettingsFeedRepository {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    override fun saveFeedDisplayMode(mode: DisplayMode) {
        sharedPreferences.edit().putString(KEY_FEED, mode.name).apply()
    }
    override fun getFeedDisplayMode(): DisplayMode {
        val modeString = sharedPreferences.getString(KEY_FEED, null)
        return modeString?.let { DisplayMode.valueOf(it) } ?: DisplayMode.LIST
    }
}