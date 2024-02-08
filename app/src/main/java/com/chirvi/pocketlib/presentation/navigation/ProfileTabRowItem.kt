package com.chirvi.pocketlib.presentation.navigation

sealed class ProfileTabRowItem(
    val title: String
) {
    data object MyBooks : ProfileTabRowItem(
        title = "Заглушка"
    )
    data object Favorite : ProfileTabRowItem(
        title = "Заглушка"
    )
}