package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.introductionNavGraph(
    introductionLoginContent: @Composable () -> Unit,
    introductionRegistrationContent: @Composable () -> Unit,

    ) {
    navigation(
        route = Screen.Introduction.route,
        startDestination = Screen.IntroductionLogin.route
    ) {
        composable(Screen.IntroductionLogin.route) {
            introductionLoginContent()
        }
        composable(Screen.IntroductionRegistration.route) {
            introductionRegistrationContent()
        }
    }
}