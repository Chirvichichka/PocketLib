package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chirvi.pocketlib.presentation.navigation.Screen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    addBookScreenContent: @Composable () -> Unit,
    pageBookContent: @Composable () -> Unit,
    feedContent: @Composable () -> Unit,
    userContent: @Composable () -> Unit,
    settingsContent: @Composable () -> Unit,
    registrationContent: @Composable () -> Unit,
    ) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        homeNavGraph(
            feedContent = feedContent,
            pageBookContent = pageBookContent,
        )
        profileNavGraph(
            pageBookContent = pageBookContent,
            userContent = userContent,
            settingsContent = settingsContent,
            registrationContent = registrationContent
        )
        composable(route = Screen.AddBook.route) {
            addBookScreenContent()
        }
    }
}