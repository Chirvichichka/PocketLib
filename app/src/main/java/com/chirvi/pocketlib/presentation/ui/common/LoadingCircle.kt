package com.chirvi.pocketlib.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun LoadingCircle() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = PocketLibTheme.colors.secondary
        )
    }
}