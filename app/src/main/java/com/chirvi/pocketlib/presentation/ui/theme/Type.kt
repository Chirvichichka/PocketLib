package com.chirvi.pocketlib.presentation.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chirvi.pocketlib.R

val merriweatherFamily = FontFamily(
    Font(R.font.robotoslab_variablefont_wght, FontWeight.Normal)
)


val primaryStyle = TextStyle(
    fontSize = 14.sp,
//    letterSpacing = 0.5.sp,
    fontFamily = merriweatherFamily,
    fontWeight = FontWeight.Normal
)

val primaryLargeStyle = TextStyle(
    fontSize = 16.sp,
//    letterSpacing = 0.5.sp,
    fontFamily = merriweatherFamily,
    fontWeight = FontWeight.Normal
)

val primarySmallStyle = TextStyle(
    fontSize = 12.sp,
    fontFamily = merriweatherFamily,
    fontWeight = FontWeight.Normal
)

//    lineHeight = 24.sp,