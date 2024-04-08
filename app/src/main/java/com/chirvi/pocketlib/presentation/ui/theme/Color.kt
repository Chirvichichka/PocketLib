package com.chirvi.pocketlib.presentation.ui.theme

import androidx.compose.ui.graphics.Color

val Light = Color(0xFFFFF7F1)
val SkyBlue = Color(0xFFFFE4C9)
val Blue = Color(0xFFE78895)
val Black = Color(0xFF1D242B)
val Gray = Color(0xFF7D7C7C)

val Light2 = Color(0xFFFFF7F1)
val Green = Color(0xFF739072)
val DarkGreen = Color(0xFF3A4D39)
val Black2 = Color(0xFF1D242B)

data class Colors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val selected: Color,
    val dark: Color,
    val quaternary: Color,
)

val md_theme_light_primary = Color(0xFFBD0047)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFFFD9DD)
val md_theme_light_onPrimaryContainer = Color(0xFF400012)
val md_theme_light_secondary = Color(0xFF76565A)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFFFD9DD)
val md_theme_light_onSecondaryContainer = Color(0xFF2C1518)
val md_theme_light_tertiary = Color(0xFF785831)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFFFDDB8)
val md_theme_light_onTertiaryContainer = Color(0xFF2B1700)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_onBackground = Color(0xFF201A1B)
val md_theme_light_surface = Color(0xFFFFFBFF)
val md_theme_light_onSurface = Color(0xFF201A1B)
val md_theme_light_surfaceVariant = Color(0xFFF3DDDF)
val md_theme_light_onSurfaceVariant = Color(0xFF524345)
val md_theme_light_outline = Color(0xFF847374)
val md_theme_light_inverseOnSurface = Color(0xFFFBEEEE)
val md_theme_light_inverseSurface = Color(0xFF362F2F)
val md_theme_light_inversePrimary = Color(0xFFFFB2BC)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFFBD0047)
val md_theme_light_outlineVariant = Color(0xFFD7C1C3)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFFFFB2BC)
val md_theme_dark_onPrimary = Color(0xFF670023)
val md_theme_dark_primaryContainer = Color(0xFF910034)
val md_theme_dark_onPrimaryContainer = Color(0xFFFFD9DD)
val md_theme_dark_secondary = Color(0xFFE5BDC1)
val md_theme_dark_onSecondary = Color(0xFF43292D)
val md_theme_dark_secondaryContainer = Color(0xFF5C3F43)
val md_theme_dark_onSecondaryContainer = Color(0xFFFFD9DD)
val md_theme_dark_tertiary = Color(0xFFEABF8F)
val md_theme_dark_onTertiary = Color(0xFF452B07)
val md_theme_dark_tertiaryContainer = Color(0xFF5E411C)
val md_theme_dark_onTertiaryContainer = Color(0xFFFFDDB8)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF201A1B)
val md_theme_dark_onBackground = Color(0xFFECE0E0)
val md_theme_dark_surface = Color(0xFF201A1B)
val md_theme_dark_onSurface = Color(0xFFECE0E0)
val md_theme_dark_surfaceVariant = Color(0xFF524345)
val md_theme_dark_onSurfaceVariant = Color(0xFFD7C1C3)
val md_theme_dark_outline = Color(0xFF9F8C8E)
val md_theme_dark_inverseOnSurface = Color(0xFF201A1B)
val md_theme_dark_inverseSurface = Color(0xFFECE0E0)
val md_theme_dark_inversePrimary = Color(0xFFBD0047)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFFFFB2BC)
val md_theme_dark_outlineVariant = Color(0xFF524345)
val md_theme_dark_scrim = Color(0xFF000000)


val seed = Color(0xFFFF2A69)

data class ColorsScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val error: Color,
    val errorContainer: Color,
    val onError: Color,
    val onErrorContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val inverseOnSurface: Color,
    val inverseSurface: Color,
    val inversePrimary: Color,
    val surfaceTint: Color,
    val outlineVariant: Color,
    val scrim: Color,
)

val LightMainColors = ColorsScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

val DarkMainColors = ColorsScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)
