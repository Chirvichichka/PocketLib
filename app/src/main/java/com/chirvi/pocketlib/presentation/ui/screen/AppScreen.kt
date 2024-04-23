package com.chirvi.pocketlib.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chirvi.pocketlib.presentation.ui.screen.introduction.IntroductionScreen
import com.chirvi.pocketlib.presentation.ui.screen.main.MainScreen
import com.chirvi.pocketlib.presentation.ui.theme.ColorScheme
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun AppScreen() {
    val appViewModel = hiltViewModel<AppViewModel>()
    appViewModel.getUser()

    val darkTheme by appViewModel.darkTheme.observeAsState(isSystemInDarkTheme())
    val colorScheme by appViewModel.colorScheme.observeAsState(ColorScheme.BLUE)
    val user by appViewModel.currentUser.observeAsState()

    PocketLibTheme(
        darkTheme = darkTheme,
        currentTheme = colorScheme,
        user = user
    ) {
        if (user != null) {
            MainScreen(viewModel = appViewModel, updateUser = { appViewModel.getUser() })
        } else {
            IntroductionScreen(viewModel = appViewModel)
        }
    }
}