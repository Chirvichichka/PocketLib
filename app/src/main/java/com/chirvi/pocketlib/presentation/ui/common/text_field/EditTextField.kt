package com.chirvi.pocketlib.presentation.ui.common.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun EditTextField(
    label: String,
    text: String,
    onValueChange: () -> Unit,
    ) {
    val textStyle = PocketLibTheme.textStyles.normalStyle

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange() },
        label = {
            Text(
                text = label,
                style = PocketLibTheme.textStyles.normalStyle
            )
        },
        textStyle = textStyle,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = PocketLibTheme.colors.primary,

            focusedContainerColor = PocketLibTheme.colors.background,
            focusedIndicatorColor = PocketLibTheme.colors.primary,
            focusedTextColor = PocketLibTheme.colors.onBackground,
            focusedLabelColor = PocketLibTheme.colors.primary,

            unfocusedContainerColor = PocketLibTheme.colors.background,
            unfocusedIndicatorColor = PocketLibTheme.colors.secondary,
            unfocusedTextColor = PocketLibTheme.colors.onBackground,
            unfocusedLabelColor = PocketLibTheme.colors.secondary
        )
    )
}