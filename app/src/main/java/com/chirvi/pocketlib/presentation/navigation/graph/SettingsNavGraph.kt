package com.chirvi.pocketlib.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chirvi.pocketlib.presentation.navigation.Screen

fun NavGraphBuilder.settingsNavGraph(
    registrationContent: @Composable () -> Unit,
    settingsContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.SettingsPage.route,
        route = Screen.Settings.route
    ) {
        composable(route = Screen.SettingsPage.route) {
            settingsContent()
        }
        composable(route = Screen.Registration.route) {
            registrationContent()
        }
    }
}