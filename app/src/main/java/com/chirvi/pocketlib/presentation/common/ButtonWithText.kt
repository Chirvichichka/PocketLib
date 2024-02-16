package com.chirvi.pocketlib.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun ButtonWithText(
    text: String,
    colorScheme: Boolean = true,
    onClickListener: () -> Unit
) {
    val containerColor: Color
    val textColor: Color

    if (colorScheme) {
        containerColor = PocketLibTheme.colors.tertiary
        textColor = PocketLibTheme.colors.primary
    } else {
        containerColor = PocketLibTheme.colors.secondary
        textColor = PocketLibTheme.colors.dark
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = { onClickListener() }
    ) {
        Text(
            text = text,
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = textColor
            )
        )
    }
}