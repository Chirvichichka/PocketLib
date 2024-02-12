package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    bookPageContent: @Composable () -> Unit,
    feedContent: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.Feed.route,
        route = Screen.Home.route
    ) {
        composable(Screen.PageBook.route) {
            bookPageContent()
        }
        composable(Screen.Feed.route) {
            feedContent()
        }
    }
}