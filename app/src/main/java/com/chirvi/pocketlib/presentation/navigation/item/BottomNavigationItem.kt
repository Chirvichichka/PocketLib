package com.chirvi.pocketlib.presentation.navigation.item

import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.navigation.Screen

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
        iconId = R.drawable.person
    )
    data object AddBook : BottomNavigationItem(
        screen = Screen.AddBook,
        title = R.string.add_book,
        iconId = R.drawable.add
    )
}