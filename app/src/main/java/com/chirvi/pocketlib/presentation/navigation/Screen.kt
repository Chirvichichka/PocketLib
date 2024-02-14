package com.chirvi.pocketlib.presentation.navigation

sealed class Screen(
    val route: String
) {
    data object Home : Screen(ROUTE_HOME)
    data object Profile : Screen(ROUTE_PROFILE)
    data object AddBook : Screen(ROUTE_ADD_BOOK)
    data object PageBook : Screen(ROUTE_PAGE_BOOK)
    data object Feed : Screen(ROUTE_FEED)
    data object User : Screen(ROUTE_USER)
    data object Settings : Screen(ROUTE_SETTINGS)

    private companion object {
        const val ROUTE_SETTINGS = "settings"
        const val ROUTE_USER = "user"
        const val ROUTE_PAGE_BOOK = "book_page"
        const val ROUTE_FEED = "feed"
        const val ROUTE_HOME = "home"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_ADD_BOOK = "ddd_book"
    }
}