package com.chirvi.pocketlib.presentation.navigation.graph.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationMainState

@Composable
fun MainNavGraph(
    addBookScreenContent: @Composable () -> Unit,
    pageBookContent: @Composable (idPost: String) -> Unit,
    feedContent: @Composable () -> Unit,
    userContent: @Composable () -> Unit,
    settingsContent: @Composable () -> Unit,
    registrationContent: @Composable () -> Unit,
    loginContent: @Composable () -> Unit,
    bookViewer: @Composable (id: String) -> Unit,
) {
    val navigationMainState = LocalNavigationMainState.current
    val navHostController = navigationMainState.navHostController
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        homeNavGraph(
            feedContent = feedContent,
            pageBookContent = pageBookContent,
            bookViewer = bookViewer
        )
        profileNavGraph(
            pageBookContent = pageBookContent,
            userContent = userContent,
            settingsContent = settingsContent,
            registrationContent = registrationContent,
            loginContent = loginContent,
            bookViewer = bookViewer
        )
        composable(route = Screen.AddBook.route) {
            addBookScreenContent()
        }
    }
}