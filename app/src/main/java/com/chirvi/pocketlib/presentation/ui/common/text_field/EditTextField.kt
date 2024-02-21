package com.chirvi.pocketlib.presentation.ui.common.text_field

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun EditTextField(
    text: String,
    onValueChange: () -> Unit,
    ) {
    val textStyle = PocketLibTheme.textStyles.normalStyle
    var enabled by remember { mutableStateOf(false) }

    val iconTint = if(enabled) {
        PocketLibTheme.colors.dark
    } else {
        PocketLibTheme.colors.primary
    }

    TextField(
        value = text,
        onValueChange = { onValueChange() },
        enabled = enabled,
        trailingIcon = {
            IconButton(
                onClick = { enabled = !enabled }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = null,
                    tint = iconTint
                )
            }
        },
        textStyle = textStyle,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = PocketLibTheme.colors.dark,

            focusedContainerColor = PocketLibTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = PocketLibTheme.colors.dark,
            focusedPlaceholderColor = PocketLibTheme.colors.dark,

            unfocusedContainerColor = PocketLibTheme.colors.secondary,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = PocketLibTheme.colors.dark,
            unfocusedPlaceholderColor = PocketLibTheme.colors.dark,

            disabledContainerColor = PocketLibTheme.colors.tertiary,
            disabledIndicatorColor = Color.Transparent,
            disabledTextColor = PocketLibTheme.colors.primary,
            disabledPlaceholderColor = PocketLibTheme.colors.primary,
        )
    )
}