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
            navHostController.graph.parent?.let {
                popUpTo(it.id) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
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