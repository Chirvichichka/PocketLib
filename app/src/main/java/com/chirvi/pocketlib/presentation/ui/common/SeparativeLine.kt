package com.chirvi.pocketlib.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme


@Composable
fun SeparativeLine() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .height(0.4.dp)
            .background(color = PocketLibTheme.colors.outline)
    )
}