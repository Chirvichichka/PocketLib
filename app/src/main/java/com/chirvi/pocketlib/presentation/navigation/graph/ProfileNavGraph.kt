package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.profileNavGraph(
    pageBookContent: @Composable () -> Unit,
    userContent: @Composable () -> Unit,
    settingsContent: @Composable () -> Unit
    ) {
    navigation(
        startDestination = Screen.User.route,
        route = Screen.Profile.route
    ) {
        composable(Screen.User.route) {
            userContent()
        }
        composable(Screen.PageBookProfile.route) {
            pageBookContent()
        }
        composable(Screen.Settings.route) {
            settingsContent()
        }
    }
}