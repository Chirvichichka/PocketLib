package com.chirvi.pocketlib.presentation.navigation.graph.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    pageBookContent: @Composable (String) -> Unit,
    feedContent: @Composable () -> Unit,
    bookViewer: @Composable (String) -> Unit,
) {
    navigation(
        startDestination = Screen.Feed.route,
        route = Screen.Home.route
    ) {
        bookFeedNavGraph(
            pageBookContent = pageBookContent,
            bookViewer = bookViewer
        )
        composable(Screen.Feed.route) {
            feedContent()
        }
    }
}