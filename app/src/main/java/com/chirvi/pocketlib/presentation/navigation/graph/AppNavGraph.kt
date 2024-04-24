package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState

@Composable
fun AppNavGraph(
    startDestination: String,
    addBookScreenContent: @Composable () -> Unit,
    bookPageContent: @Composable (idPost: String) -> Unit,
    feedContent: @Composable () -> Unit,
    userContent: @Composable () -> Unit,
    settingsContent: @Composable () -> Unit,
    registrationContent: @Composable () -> Unit,
    loginContent: @Composable () -> Unit,
    bookViewer: @Composable (id: String) -> Unit,
    introductionLoginContent: @Composable () -> Unit,
    introductionRegistrationContent: @Composable () -> Unit,
) {
    val navigationState = LocalNavigationState.current

    NavHost(
        navController = navigationState.navHostController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        homeNavGraph(
            feedContent = feedContent,
            pageBookContent = bookPageContent,
            bookViewer = bookViewer
        )
        profileNavGraph(
            pageBookContent = bookPageContent,
            userContent = userContent,
            settingsContent = settingsContent,
            registrationContent = registrationContent,
            loginContent = loginContent,
            bookViewer = bookViewer
        )
        composable(route = Screen.AddBook.route) {
            addBookScreenContent()
        }
        introductionNavGraph(
            introductionLoginContent = introductionLoginContent,
            introductionRegistrationContent = introductionRegistrationContent
        )
    }
}