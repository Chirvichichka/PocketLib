package com.chirvi.pocketlib.presentation.ui.screen.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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
import com.chirvi.pocketlib.presentation.ui.screen.main.book_add.AddBookScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page.BookPageScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page.book_viewer.BookViewer
import com.chirvi.pocketlib.presentation.ui.screen.main.home.feed.FeedScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.introduction.login.IntroductionLoginScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.introduction.registration.IntroductionRegistrationScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.UserScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.SettingsScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.create_account.CreateAccountScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.login.LoginScreen
import com.chirvi.pocketlib.presentation.ui.theme.ColorScheme
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    viewModel.getUser()

    val darkTheme by viewModel.darkTheme.observeAsState(isSystemInDarkTheme())
    val colorScheme by viewModel.colorScheme.observeAsState(ColorScheme.BLUE)

    val user by viewModel.currentUser.observeAsState()
    val startDestination by viewModel.startDestination.observeAsState(Screen.Home.route)

    PocketLibTheme(
        darkTheme = darkTheme,
        currentTheme = colorScheme,
        user = user,
        startDestination = startDestination
    ) {
        Content(startDestination = startDestination, viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    startDestination: String,
    viewModel: MainViewModel,
) {
    val scroll = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scroll.nestedScrollConnection),
        containerColor = PocketLibTheme.colors.background,
        bottomBar = {
            if(startDestination == Screen.Home.route) {
                BottomNavigation()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            AppNavGraph(
                startDestination = startDestination,
                addBookScreenContent = { AddBookScreen() },
                feedContent = { FeedScreen(scroll = scroll) },
                bookPageContent = { id -> BookPageScreen(idPost = id,) },
                userContent = { UserScreen() },
                settingsContent = {
                    SettingsScreen(
                        changeTheme = { viewModel.changeTheme() },
                        changeColorScheme = { newColorScheme ->
                            viewModel.changeColorScheme(
                                newColorScheme
                            )
                        },
                        updateUser = { viewModel.getUser() }
                    )
                },
                registrationContent = { CreateAccountScreen(updateUser = { viewModel.getUser() }) },
                loginContent = { LoginScreen(updateUser = { viewModel.getUser() }) },
                bookViewer = { id -> BookViewer(id = id) },
                introductionLoginContent = { IntroductionLoginScreen(updateUser = { viewModel.getUser() }) },
                introductionRegistrationContent = { IntroductionRegistrationScreen() }
            )
        }
    }
}

@Composable
private fun BottomNavigation() {
    val navigationState = LocalNavigationState.current
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

    val items = listOf(
        BottomNavigationItem.AddBook,
        BottomNavigationItem.Home,
        BottomNavigationItem.Profile,
    )

    BottomAppBar(
        tonalElevation = 20.dp,
        containerColor = PocketLibTheme.colors.secondaryContainer,
        actions = {
            items.forEach{ item ->
                val selected = navBackStackEntry?.destination?.hierarchy?.any {
                    it.route == item.screen.route
                } ?: false

                NavigationBarItem(
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PocketLibTheme.colors.secondary,
                        selectedTextColor = PocketLibTheme.colors.secondary,
                        unselectedIconColor = PocketLibTheme.colors.onSecondaryContainer,
                        unselectedTextColor = PocketLibTheme.colors.onSecondaryContainer,
                        indicatorColor = PocketLibTheme.colors.onSecondary
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
    )
}