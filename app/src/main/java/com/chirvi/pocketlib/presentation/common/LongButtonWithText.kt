package com.chirvi.pocketlib.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun ButtonWithText(
    text: String,
    onClickListener: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = PocketLibTheme.colors.tertiary
        ),
        onClick = {
            onClickListener()
        }
    ) {
        Text(
            text = text,
            style = PocketLibTheme.textStyles.primarySmall
        )
    }
}