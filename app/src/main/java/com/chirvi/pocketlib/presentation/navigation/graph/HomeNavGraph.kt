package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    pageBookContent: @Composable () -> Unit,
    feedContent: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.Feed.route,
        route = Screen.Home.route
    ) {
        composable(Screen.PageBook.route) {
            pageBookContent()
        }
        composable(Screen.Feed.route) {
            feedContent()
        }
    }
}