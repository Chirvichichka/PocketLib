package com.chirvi.pocketlib.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.navigation.AppNavGraph
import com.chirvi.pocketlib.presentation.navigation.NavigationState
import com.chirvi.pocketlib.presentation.navigation.rememberNavigationState
import com.chirvi.pocketlib.presentation.ui.screen.home.HomeScreen
import com.chirvi.pocketlib.presentation.ui.screen.profile.ProfileScreen
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import com.chirvi.pocketlib.presentation.navigation.NavigationItem
import com.chirvi.pocketlib.presentation.ui.screen.book_add.AddBookScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                navigationState = navigationState
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(PocketLibTheme.colors.primary)
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            AppNavGraph(
                navHostController = navigationState.navHostController,
                homeScreenContent = { HomeScreen() },
                profileScreenContent = { ProfileScreen() },
                addBookScreenContent = { AddBookScreen() },
            )
        }
    }
}

@Composable
private fun BottomNavigation(
    navigationState: NavigationState
) {
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Profile
    )
    val addBook = NavigationItem.AddBook

    BottomAppBar(
        containerColor = PocketLibTheme.colors.tertiary,
        actions = {
            items.forEach{ item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = PocketLibTheme.colors.secondary
                    ),
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navigationState.navigateTo(item.screen.route)
                    },
                    icon = {
                       Icon(
                           painter = painterResource(id = item.iconId),
                           contentDescription = null,
                           tint = PocketLibTheme.colors.black)
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            style = PocketLibTheme.textStyles.primary.copy(
                                color = PocketLibTheme.colors.secondary
                            )
                        )
                    }
                )
            }
        },
        floatingActionButton = {
            val containerColor: Color
            val tint: Color

            if(currentRoute == addBook.screen.route) {
                containerColor = PocketLibTheme.colors.secondary
                tint = PocketLibTheme.colors.black
            } else {
                containerColor = PocketLibTheme.colors.black
                tint = PocketLibTheme.colors.secondary
            }

            SmallFloatingActionButton(
                onClick = { navigationState.navigateTo(addBook.screen.route) },
                containerColor = containerColor,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation =  0.dp,
                    pressedElevation = 0.dp
                ),
            ) {
                Icon(
                    tint = tint,
                    painter = painterResource(id = addBook.iconId),
                    contentDescription = null
                )
            }
        }
    )
}