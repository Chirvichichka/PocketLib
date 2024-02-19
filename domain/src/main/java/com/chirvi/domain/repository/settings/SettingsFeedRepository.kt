package com.chirvi.domain.repository.settings

import com.chirvi.domain.models.DisplayMode

interface SettingsFeedRepository{
    fun saveFeedDisplayMode(mode: DisplayMode)
    fun getFeedDisplayMode(): DisplayMode
}
