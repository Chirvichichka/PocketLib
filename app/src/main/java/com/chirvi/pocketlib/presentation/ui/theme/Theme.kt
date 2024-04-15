package com.chirvi.pocketlib.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.chirvi.pocketlib.presentation.models.UserPresentation
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import com.chirvi.pocketlib.presentation.navigation.state.rememberNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.blue_theme.DarkBlueTheme
import com.chirvi.pocketlib.presentation.ui.theme.blue_theme.LightBlueTheme
import com.chirvi.pocketlib.presentation.ui.theme.green_theme.DarkGreenTheme
import com.chirvi.pocketlib.presentation.ui.theme.green_theme.LightGreenTheme
import com.chirvi.pocketlib.presentation.ui.theme.pink_theme.DarkPinkColors
import com.chirvi.pocketlib.presentation.ui.theme.pink_theme.LightPinkColors

private val LocalColorScheme = staticCompositionLocalOf<ColorScheme> {
    error("No text styles provided")
}

val LocalNavigationState = staticCompositionLocalOf<NavigationState> {
    error("No MainNavigationState provided")
}

val LocalUser = staticCompositionLocalOf<UserPresentation?> {
    error("No user")
}

private val LocalTextStyles = staticCompositionLocalOf<TextStyles> {
    error("No text styles provided")
}

private val LocalColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

@Composable
fun PocketLibTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    currentTheme: ColorScheme = LocalColorScheme.current,
    user: UserPresentation?,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current

    val colorScheme: Colors = when(currentTheme) {
        ColorScheme.BLUE -> {
            if(darkTheme) { DarkBlueTheme } else { LightBlueTheme }
        }
        ColorScheme.PINK -> {
            if(darkTheme) { DarkPinkColors } else { LightPinkColors }
        }
        ColorScheme.GREEN -> {
            if(darkTheme) { DarkGreenTheme } else { LightGreenTheme }
        }
    }

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.secondaryContainer.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalUser provides user,
        LocalNavigationState provides rememberNavigationState(),
        LocalColors provides colorScheme,
        LocalTextStyles provides TextStyleType,
        content = content
    )
}

object PocketLibTheme {
    val colors: Colors
        @Composable
        get() = LocalColors.current

    val textStyles: TextStyles
        @Composable
        get() = LocalTextStyles.current
}