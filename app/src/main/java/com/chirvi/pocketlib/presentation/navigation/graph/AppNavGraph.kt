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
    profileScreenContent: @Composable () -> Unit,
    addBookScreenContent: @Composable () -> Unit,
    bookPageContent: @Composable () -> Unit,
    feedContent: @Composable () -> Unit
    ) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        homeNavGraph(
            feedContent = feedContent,
            bookPageContent = bookPageContent
        )
        composable(route = Screen.Profile.route) {
            profileScreenContent()
        }
        composable(route = Screen.AddBook.route) {
            addBookScreenContent()
        }
    }
}