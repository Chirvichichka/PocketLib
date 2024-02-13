package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.profileNavGraph(
    profilePageBookContent: @Composable () -> Unit,
    userContent: @Composable () -> Unit
    ) {
    navigation(
        startDestination = Screen.User.route,
        route = Screen.Profile.route
    ) {
        composable(Screen.User.route) {
            userContent()
        }
        composable(Screen.PageBook.route) {
            profilePageBookContent()
        }
    }
}