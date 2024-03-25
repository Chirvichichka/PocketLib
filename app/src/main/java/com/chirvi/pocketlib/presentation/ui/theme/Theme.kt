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

private val MainColorScheme = Colors( //todo узнать как меня тему
    primary = Light,
    secondary = SkyBlue,
    tertiary = Blue,
    selected = SkyBlue,
    dark = Black,
    quaternary = Gray
)

private val SecondaryColorScheme = Colors( //todo переименовать
    primary = Light2,
    secondary = Green,
    tertiary = DarkGreen,
    selected = Green,
    dark = Black2,
    quaternary = Black2
)

val TextStyleType = TextStyles(
    normalStyle = normalStyle,
    largeStyle = largeStyle,
    smallStyle = smallStyle,
    topAppBarStyle = topAppBarStyle
)

private val localColorScheme = staticCompositionLocalOf<TextStyles> {
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
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    val colorScheme = SecondaryColorScheme

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.tertiary.toArgb()
            window.navigationBarColor = colorScheme.tertiary.toArgb()
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

}