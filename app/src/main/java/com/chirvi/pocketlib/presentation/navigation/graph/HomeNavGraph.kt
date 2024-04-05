package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    pageBookContent: @Composable (String) -> Unit,
    feedContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.Feed.route,
        route = Screen.Home.route
    ) {
        composable(Screen.PageBookHome.route) {
            val id = it.arguments?.getString("feed_post_id") ?: ""
            pageBookContent(id)
        }
        composable(Screen.Feed.route) {
            feedContent()
        }
    }
}