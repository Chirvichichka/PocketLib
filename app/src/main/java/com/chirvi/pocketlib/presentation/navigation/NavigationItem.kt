package com.chirvi.pocketlib.presentation.navigation

import com.chirvi.pocketlib.R

sealed class NavigationItem(
    val screen: Screen,
    val title: Int,
    val iconId: Int
) {
    data object Home : NavigationItem(
        screen = Screen.Home,
        title = R.string.home,
        iconId = R.drawable.home_icon
    )
    data object Profile : NavigationItem(
        screen = Screen.Profile,
        title = R.string.profile,
        iconId = R.drawable.profile_icon
    )
    data object AddBook : NavigationItem(
        screen = Screen.AddBook,
        title = R.string.add_book,
        iconId = R.drawable.add
    )
}