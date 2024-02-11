package com.chirvi.pocketlib.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chirvi.pocketlib.presentation.navigation.AppNavGraph
import com.chirvi.pocketlib.presentation.navigation.BottomNavigationItem
import com.chirvi.pocketlib.presentation.navigation.NavigationState
import com.chirvi.pocketlib.presentation.navigation.rememberNavigationState
import com.chirvi.pocketlib.presentation.ui.screen.book_add.AddBookScreen
import com.chirvi.pocketlib.presentation.ui.screen.home.HomeScreen
import com.chirvi.pocketlib.presentation.ui.screen.profile.ProfileScreen
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val scroll = TopAppBarDefaults.enterAlwaysScrollBehavior() //todo перенести в HomeScreen
    Scaffold(
        containerColor = PocketLibTheme.colors.primary,
        modifier = Modifier.nestedScroll(scroll.nestedScrollConnection),
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
                homeScreenContent = { HomeScreen(scroll = scroll) },
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
        BottomNavigationItem.Home,
        BottomNavigationItem.Profile
    )
    val addBook = BottomNavigationItem.AddBook

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
                           tint = PocketLibTheme.colors.black
                       )
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            style = PocketLibTheme.textStyles.primarySmall.copy(
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