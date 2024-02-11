package com.chirvi.pocketlib.presentation.navigation

import com.chirvi.pocketlib.R

sealed class BottomNavigationItem(
    val screen: Screen,
    val title: Int,
    val iconId: Int
) {
    data object Home : BottomNavigationItem(
        screen = Screen.Home,
        title = R.string.home,
        iconId = R.drawable.home
    )
    data object Profile : BottomNavigationItem(
        screen = Screen.Profile,
        title = R.string.profile,
        iconId = R.drawable.profile
    )
    data object AddBook : BottomNavigationItem(
        screen = Screen.AddBook,
        title = R.string.add_book,
        iconId = R.drawable.add
    )
}