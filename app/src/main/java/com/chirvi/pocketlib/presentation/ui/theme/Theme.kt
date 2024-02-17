package com.chirvi.pocketlib.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

private val MainColorScheme = Colors( //todo узнать как меня тему
    primary = Light,
    secondary = SkyBlue,
    tertiary = Blue,
    selected = SkyBlue,
    dark = Black,
    quaternary = Gray
)

private val SecondaryColorScheme = Colors(
    primary = Light,
    secondary = SkyBlue,
    tertiary = Blue,
    selected = SkyBlue,
    dark = Black,
    quaternary = Gray
)

data class TextStyles(
    val smallStyle: TextStyle,
    val largeStyle: TextStyle,
    val normalStyle: TextStyle,
    val topAppBarStyle: TextStyle
)

val TextStyleType = TextStyles(
    normalStyle = normalStyle,
    largeStyle = largeStyle,
    smallStyle = smallStyle,
    topAppBarStyle = topAppBarStyle
)

private val localTextStyles = staticCompositionLocalOf<TextStyles> {
    error("No text styles provided")
}

private val localColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

@Composable
fun PocketLibTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
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