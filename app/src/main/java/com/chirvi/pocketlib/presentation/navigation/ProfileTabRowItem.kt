package com.chirvi.pocketlib.presentation.navigation

import com.chirvi.pocketlib.R

sealed class ProfileTabRowItem(
    val title: String,
    val iconId: Int,
) {
    data object MyBooks : ProfileTabRowItem(
        title = "Заглушка",
        iconId = R.drawable.round_library_add
    )
    data object Favorite : ProfileTabRowItem(
        title = "Заглушка",
        iconId = R.drawable.favorite_icon
    )
}