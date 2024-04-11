package com.chirvi.pocketlib.presentation.ui.screen.main

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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
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
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.login.LoginScreen
import com.chirvi.pocketlib.presentation.ui.theme.ColorScheme
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

val localFirebaseUser = staticCompositionLocalOf<FirebaseUser?> { null }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()
    val scroll = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var darkTheme by remember { mutableStateOf(true) }
    var colorScheme by remember { mutableStateOf(ColorScheme.BLUE) }

    val firebaseUser = remember { mutableStateOf<FirebaseUser?>(null) }
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    firebaseUser.value = currentUser

    CompositionLocalProvider(
        localFirebaseUser provides firebaseUser.value
    ) {
        PocketLibTheme(
            darkTheme = darkTheme,
            currentTheme = colorScheme
        ) {
            Scaffold(
                modifier = Modifier.nestedScroll(scroll.nestedScrollConnection),
                containerColor = PocketLibTheme.colors.background,
                bottomBar = {
                    BottomNavigation(
                        navigationState = navigationState
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
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
                                themeChange = { darkTheme = !darkTheme },
                                onBackPressed = { navigationState.navHostController.popBackStack() },
                                onCreateAccountClick = { navigationState.navigateTo(Screen.Registration.route) },
                                colorSchemeChange = { colorScheme = it },
                                onLoginClick = { navigationState.navigateTo(Screen.Login.route) }
                            )
                        },
                        registrationContent = {
                            CreateAccountScreen(
                                onBackPressed = { navigationState.navHostController.popBackStack() },
                                toHomeScreen = { navigationState.navigateTo(Screen.Feed.route) }
                            )
                        },
                        loginContent = {
                            LoginScreen(
                                onBackPressed = { navigationState.navHostController.popBackStack() },
                                toHomeScreen = { currentUser ->
                                    firebaseUser.value = currentUser
                                    navigationState.navigateTo(Screen.Feed.route)
                                }
                            )
                        }
                    )
                }
            }
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
        tonalElevation = 20.dp,
        containerColor = PocketLibTheme.colors.surfaceVariant,
        actions = {
            items.forEach{ item ->

                val selected = navBackStackEntry?.destination?.hierarchy?.any {
                    it.route == item.screen.route
                } ?: false

                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PocketLibTheme.colors.primary,
                        selectedTextColor = PocketLibTheme.colors.primary,
                        unselectedIconColor = PocketLibTheme.colors.onPrimaryContainer,
                        unselectedTextColor = PocketLibTheme.colors.onPrimaryContainer,
                        indicatorColor = PocketLibTheme.colors.onPrimary
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
                       )
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            style = PocketLibTheme.textStyles.smallStyle
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
                containerColor = PocketLibTheme.colors.onPrimary
                tint = PocketLibTheme.colors.onPrimaryContainer
            } else {
                containerColor = PocketLibTheme.colors.onPrimaryContainer
                tint = PocketLibTheme.colors.onPrimary
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