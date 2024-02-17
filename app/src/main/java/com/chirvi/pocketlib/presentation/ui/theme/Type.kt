package com.chirvi.pocketlib.presentation.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chirvi.pocketlib.R

val merriweatherFamily = FontFamily(
    Font(R.font.robotoslab_variablefont_wght, FontWeight.Normal)
)

data class TextStyles(
    val smallStyle: TextStyle,
    val largeStyle: TextStyle,
    val normalStyle: TextStyle,
    val topAppBarStyle: TextStyle
)

val normalStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = merriweatherFamily,
    fontWeight = FontWeight.Normal
)

val largeStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = merriweatherFamily,
    fontWeight = FontWeight.Normal
)

val smallStyle = TextStyle(
    fontSize = 12.sp,
    fontFamily = merriweatherFamily,
    fontWeight = FontWeight.Normal
)

val topAppBarStyle = TextStyle(
    fontSize = 16.sp,
    fontStyle = FontStyle.Italic,
    fontFamily = merriweatherFamily,
    fontWeight = FontWeight.Normal
)