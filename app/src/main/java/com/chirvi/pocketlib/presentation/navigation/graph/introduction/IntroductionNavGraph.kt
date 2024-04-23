package com.chirvi.pocketlib.presentation.navigation.graph.introduction

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationIntroductionState

@Composable
fun IntroductionNavGraph(
    introductionLoginContent: @Composable () -> Unit,
) {
    val navigationIntroductionState = LocalNavigationIntroductionState.current
    val navHostController = navigationIntroductionState.navHostController

    NavHost(
        navController = navHostController,
        startDestination = Screen.IntroductionLogin.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(route = Screen.IntroductionLogin.route) {
            introductionLoginContent()
        }
    }
}