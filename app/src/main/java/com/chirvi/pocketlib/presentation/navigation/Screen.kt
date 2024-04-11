package com.chirvi.pocketlib.presentation.navigation

sealed class Screen(
    val route: String
) {
    data object Home : Screen(ROUTE_HOME)
    data object Profile : Screen(ROUTE_PROFILE)
    data object AddBook : Screen(ROUTE_ADD_BOOK)
    data object PageBookHome : Screen(ROUTE_PAGE_BOOK_FEED) {
        private const val ROUTE_FOR_ARGS = "book_page_feed"
        fun getRouteWithArgs(id: String) : String {
            return "$ROUTE_FOR_ARGS/$id"
        }
    }
    data object Login : Screen(ROUTE_LOGIN)
    data object PageBookProfile : Screen(ROUTE_PAGE_BOOK_PROFILE)
    data object Feed : Screen(ROUTE_FEED)
    data object User : Screen(ROUTE_USER)
    data object Settings : Screen(ROUTE_SETTINGS)
    data object Registration : Screen(ROUTE_REGISTRATION)
    data object SettingsPage : Screen(ROUTE_SETTINGS_PAGE)

    private companion object {
        const val ROUTE_LOGIN = "login"
        const val ROUTE_REGISTRATION = "registration"
        const val ROUTE_SETTINGS = "settings"
        const val ROUTE_SETTINGS_PAGE = "settings_page"
        const val ROUTE_USER = "user"
        const val ROUTE_PAGE_BOOK_FEED = "book_page_feed/{feed_post_id}"
        const val ROUTE_PAGE_BOOK_PROFILE = "book_page_profile"
        const val ROUTE_FEED = "feed"
        const val ROUTE_HOME = "home"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_ADD_BOOK = "ddd_book"
    }
}