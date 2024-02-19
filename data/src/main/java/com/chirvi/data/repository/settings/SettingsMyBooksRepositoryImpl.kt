package com.chirvi.data.repository.settings

import android.content.Context
import android.content.SharedPreferences
import com.chirvi.domain.models.DisplayMode
import com.chirvi.domain.repository.settings.SettingsMyBooksRepository

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_MY_BOOKS = "my_books"
class SettingsMyBooksRepositoryImpl(context: Context) : SettingsMyBooksRepository {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveMyBooksDisplayMode(mode: DisplayMode) {
        sharedPreferences.edit().putString(KEY_MY_BOOKS, mode.name).apply()
    }
    override fun getMyBooksDisplayMode(): DisplayMode {
        val modeString = sharedPreferences.getString(KEY_MY_BOOKS, null)
        return modeString?.let { DisplayMode.valueOf(it) } ?: DisplayMode.LIST
    }
}