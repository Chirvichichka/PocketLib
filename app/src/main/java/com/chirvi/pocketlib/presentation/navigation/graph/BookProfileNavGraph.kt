package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.bookProfileNavGraph(
    pageBookContent: @Composable (String) -> Unit,
    bookViewer: @Composable (String) -> Unit,
) {
    navigation(
        startDestination = Screen.PageBookProfile.route,
        route = Screen.BookProfile.route
    ) {
        composable(Screen.PageBookProfile.route) {
            val id = it.arguments?.getString("profile_post_id") ?: ""
            pageBookContent(id)
        }
        composable(Screen.BookViewerProfile.route) {
            val id = it.arguments?.getString("book_viewer_profile_book_id") ?: ""
            bookViewer(id)
        }
    }
}