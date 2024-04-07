package com.chirvi.pocketlib.presentation.ui.screen.main

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.graph.AppNavGraph
import com.chirvi.pocketlib.presentation.navigation.item.BottomNavigationItem
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import com.chirvi.pocketlib.presentation.navigation.state.rememberNavigationState
import com.chirvi.pocketlib.presentation.ui.screen.main.book_add.AddBookScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page.BookPageScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.home.feed.FeedScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.home.feed.FeedViewModel
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.UserScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.SettingsScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.create_account.CreateAccountScreen
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val scroll = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scroll.nestedScrollConnection),
        containerColor = PocketLibTheme.colors.primary,
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
                addBookScreenContent = {
                    val feedViewModel = hiltViewModel<FeedViewModel>()
                    AddBookScreen(
                        toHomeScreen = {
                            navigationState.navigateTo(Screen.Feed.route)
                            feedViewModel.loadData()
                        }
                    )
                },
                feedContent = {
                    FeedScreen(
                        scroll = scroll,
                        onClickPreview = {
                            navigationState.navigateToPost(
                                id = it,
                            )
                        },
                    )
                },
                pageBookContent = {
                    BookPageScreen(
                        idPost = it,
                        onBackPressed = { navigationState.navHostController.popBackStack() }
                    )
                },
                userContent = {
                    UserScreen(
                        onClickPreview = { navigationState.navigateTo(route = Screen.PageBookProfile.route) },
                        onClickSettings = { navigationState.navigateTo(Screen.Settings.route) },
                        onClickEdit = { navigationState.navigateTo(Screen.Settings.route) }
                    )
                },
                settingsContent = {
                    SettingsScreen(
                        onBackPressed = { navigationState.navHostController.popBackStack() },
                        onCreateAccountClick = { navigationState.navigateTo(Screen.Registration.route) }
                    )
                },
                registrationContent = {
                    CreateAccountScreen(
                        onBackPressed = { navigationState.navHostController.popBackStack() }
                    )
                }
            )
        }
    }
}

@Composable
private fun BottomNavigation(
    navigationState: NavigationState
) {
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Profile
    )
    val addBook = BottomNavigationItem.AddBook

    BottomAppBar(
        containerColor = PocketLibTheme.colors.tertiary,
        actions = {
            items.forEach{ item ->

                val selected = navBackStackEntry?.destination?.hierarchy?.any {
                    it.route == item.screen.route
                } ?: false

                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = PocketLibTheme.colors.secondary
                    ),
                    selected = selected,
                    onClick = {
                        if(!selected) {
                            navigationState.navigateToWithSaveState(item.screen.route)
                        }
                    },
                    icon = {
                       Icon(
                           painter = painterResource(id = item.iconId),
                           contentDescription = null,
                           tint = PocketLibTheme.colors.dark
                       )
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            style = PocketLibTheme.textStyles.smallStyle.copy(
                                color = PocketLibTheme.colors.secondary
                            )
                        )
                    }
                )
            }
        },
        floatingActionButton = {
            val currentRoute = navBackStackEntry?.destination?.route
            val containerColor: Color
            val tint: Color

            if(currentRoute == addBook.screen.route) {
                containerColor = PocketLibTheme.colors.secondary
                tint = PocketLibTheme.colors.dark
            } else {
                containerColor = PocketLibTheme.colors.dark
                tint = PocketLibTheme.colors.secondary
            }

            SmallFloatingActionButton(
                onClick = { navigationState.navigateToWithSaveState(addBook.screen.route) },
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