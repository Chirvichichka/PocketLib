package com.chirvi.pocketlib.presentation.navigation.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationIntroductionState(
    val navHostController: NavHostController
) {
    fun navigateToWithSaveState(
        route: String
    ) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }


    fun navigateTo(
        route: String
    ) {
        navHostController.navigate(route = route)
    }

}

@Composable
fun rememberNavigationIntroductionState(
    navHostController: NavHostController = rememberNavController()
) : NavigationIntroductionState {
    return remember {
        NavigationIntroductionState(navHostController = navHostController)
    }
}