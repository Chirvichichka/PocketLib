package com.chirvi.pocketlib.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    addBookScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(route = Screen.Home.route) {
            homeScreenContent()
        }
        composable(route = Screen.Profile.route) {
            profileScreenContent()
        }
        composable(route = Screen.AddBook.route) {
            addBookScreenContent()
        }
    }
}