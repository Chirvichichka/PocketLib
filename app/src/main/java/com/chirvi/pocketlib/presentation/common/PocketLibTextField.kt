package com.chirvi.pocketlib.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun PocketLibTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolderText: String,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit
) {
    val textStyle = PocketLibTheme.textStyles.normalStyle
    var enabled by remember{ mutableStateOf(true) }

    val iconId: Int = if (enabled) {
        R.drawable.check
    } else {
        R.drawable.close
    }

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeHolderText,
                style = textStyle
            )
        },
        enabled = enabled,
        trailingIcon = {
            IconButton(
                onClick = {
                    enabled = !enabled
                }
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = null
                )
            }
        },
        textStyle = textStyle,
        singleLine = singleLine,
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