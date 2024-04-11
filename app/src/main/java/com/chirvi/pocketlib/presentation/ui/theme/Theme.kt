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
import com.chirvi.pocketlib.presentation.ui.theme.blue_theme.DarkBlueTheme
import com.chirvi.pocketlib.presentation.ui.theme.blue_theme.LightBlueTheme
import com.chirvi.pocketlib.presentation.ui.theme.green_theme.DarkGreenTheme
import com.chirvi.pocketlib.presentation.ui.theme.green_theme.LightGreenTheme
import com.chirvi.pocketlib.presentation.ui.theme.pink_theme.DarkPinkColors
import com.chirvi.pocketlib.presentation.ui.theme.pink_theme.LightPinkColors

private val localColorScheme = staticCompositionLocalOf<ColorScheme> {
    error("No text styles provided")
}

private val localTextStyles = staticCompositionLocalOf<TextStyles> {
    error("No text styles provided")
}

private val localColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

@Composable
fun PocketLibTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    currentTheme: ColorScheme = localColorScheme.current,
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
            window.statusBarColor = colorScheme.surfaceVariant.toArgb()
            window.navigationBarColor = colorScheme.surfaceVariant.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        localColors provides colorScheme,
        localTextStyles provides TextStyleType,
        content = content
    )
}

object PocketLibTheme {
    val colors: Colors
        @Composable
        get() = localColors.current

    val textStyles: TextStyles
        @Composable
        get() = localTextStyles.current

    val colorScheme: ColorScheme
        @Composable
        get() = localColorScheme.current
}