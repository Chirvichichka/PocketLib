package com.chirvi.pocketlib.presentation.ui.screen.introduction

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.chirvi.pocketlib.presentation.navigation.graph.introduction.IntroductionNavGraph
import com.chirvi.pocketlib.presentation.ui.screen.AppViewModel
import com.chirvi.pocketlib.presentation.ui.screen.introduction.login.IntroductionLoginScreen
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun IntroductionScreen(
    viewModel: AppViewModel
) {
    val view = LocalView.current

    val statusBarColor = PocketLibTheme.colors.background.toArgb()
    val navigationBarColor = PocketLibTheme.colors.background.toArgb()

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = statusBarColor
            window.navigationBarColor = navigationBarColor
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
            .padding(all = 8.dp),
    ) {
        IntroductionNavGraph(
            introductionLoginContent = { IntroductionLoginScreen(updateUser = { viewModel.getUser() }) }
        )
    }
}