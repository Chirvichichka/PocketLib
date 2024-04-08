package com.chirvi.pocketlib.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

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

private val localColors = staticCompositionLocalOf<ColorsScheme> {
    error("No colors provided")
}

@Composable
fun PocketLibTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val view = LocalView.current

    val colorScheme = if(darkTheme) {
        DarkMainColors
    } else {
        LightMainColors
    }

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.secondaryContainer.toArgb()
            window.navigationBarColor = colorScheme.secondaryContainer.toArgb()
        }
    }

    CompositionLocalProvider(
        localColors provides colorScheme,
        localTextStyles provides TextStyleType,
        content = content
    )
}

object PocketLibTheme {
    val colors: ColorsScheme
        @Composable
        get() = localColors.current

    val textStyles: TextStyles
        @Composable
        get() = localTextStyles.current

}