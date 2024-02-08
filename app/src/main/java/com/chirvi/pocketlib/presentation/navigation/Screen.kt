package com.chirvi.pocketlib.presentation.navigation

sealed class Screen(
    val route: String
) {
    data object Home : Screen(ROUTE_HOME)
    data object Profile : Screen(ROUTE_PROFILE)
    data object AddBook : Screen(ROUTE_ADD_BOOK)

    private companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_ADD_BOOK = "ddd_book"
    }
}