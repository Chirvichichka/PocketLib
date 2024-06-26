package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.profileNavGraph(
    pageBookContent: @Composable (String) -> Unit,
    userContent: @Composable () -> Unit,
    settingsContent: @Composable () -> Unit,
    registrationContent: @Composable () -> Unit,
    loginContent: @Composable () -> Unit,
    bookViewer: @Composable (String) -> Unit,
) {
    navigation(
        startDestination = Screen.User.route,
        route = Screen.Profile.route
    ) {
        composable(Screen.User.route) {
            userContent()
        }
        bookProfileNavGraph(
            pageBookContent = pageBookContent,
            bookViewer = bookViewer
        )
        settingsNavGraph(
            registrationContent = registrationContent,
            settingsContent = settingsContent,
            loginContent = loginContent
        )
    }
}