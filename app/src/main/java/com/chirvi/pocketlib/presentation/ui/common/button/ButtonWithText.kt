package com.chirvi.pocketlib.presentation.ui.common.button

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
    modifier: Modifier = Modifier,
    text: String,
    onClickListener: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = PocketLibTheme.colors.primary
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = { onClickListener() }
    ) {
        Text(
            text = text,
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.onPrimary
            )
        )
    }
}