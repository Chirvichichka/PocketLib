package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.bookNavGraph(
    pageBookContent: @Composable (String) -> Unit,
    bookViewer: @Composable (String) -> Unit,
) {
    navigation(
        startDestination = Screen.PageBookHome.route,
        route = Screen.Book.route
    ) {
        composable(Screen.PageBookHome.route) {
            val id = it.arguments?.getString("feed_post_id") ?: ""
            pageBookContent(id)
        }
        composable(Screen.BookViewer.route) {
            val id = it.arguments?.getString("book_viewer_book_id") ?: ""
            bookViewer(id)
        }
    }
}