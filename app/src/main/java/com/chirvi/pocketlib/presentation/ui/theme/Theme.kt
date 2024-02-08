package com.chirvi.pocketlib.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

data class Colors(
    val primary: Color,
    val primaryText: Color,
    val secondary: Color,
    val secondaryText: Color,
    val tertiary: Color,
    val selected: Color,
    val black: Color,
)

private val MainColorScheme = Colors(
    primary = White,
    primaryText = White,
    secondary = SkyBlue,
    secondaryText = Black,
    tertiary = Blue,
    selected = SkyBlue,
    black = Black
)

data class TextStyles(
    val primary: TextStyle,
    val primaryLarge: TextStyle,
    val primarySmall: TextStyle,
)

val TextStyleType = TextStyles(
    primary = primarySmallStyle,
    primaryLarge = primaryLargeStyle,
    primarySmall = primaryStyle
)

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController provided")
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
    content: @Composable () -> Unit
) {

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = MainColorScheme.tertiary.toArgb()
            window.navigationBarColor = MainColorScheme.tertiary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalNavController provides rememberNavController(),
        localColors provides MainColorScheme,
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
}