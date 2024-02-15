package com.chirvi.pocketlib.presentation.navigation.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chirvi.pocketlib.presentation.navigation.Screen

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(
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

    fun navigateToIn(
        route: String
    ) {
        navHostController.navigate(route = route)
    }

}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
) : NavigationState {
    return remember {
        NavigationState(navHostController = navHostController)
    }
}