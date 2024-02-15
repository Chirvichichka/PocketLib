package com.chirvi.pocketlib.presentation.navigation

sealed class Screen(
    val route: String
) {
    data object Home : Screen(ROUTE_HOME)
    data object Profile : Screen(ROUTE_PROFILE)
    data object AddBook : Screen(ROUTE_ADD_BOOK)
    data object PageBookHome : Screen(ROUTE_PAGE_BOOK_HOME)
    data object PageBookProfile : Screen(ROUTE_PAGE_BOOK_PROFILE)

    data object Feed : Screen(ROUTE_FEED)
    data object User : Screen(ROUTE_USER)
    data object Settings : Screen(ROUTE_SETTINGS)

    private companion object {
        const val ROUTE_SETTINGS = "settings"
        const val ROUTE_USER = "user"
        const val ROUTE_PAGE_BOOK_HOME = "book_page_home"
        const val ROUTE_PAGE_BOOK_PROFILE = "book_page_profile"
        const val ROUTE_FEED = "feed"
        const val ROUTE_HOME = "home"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_ADD_BOOK = "ddd_book"
    }
}