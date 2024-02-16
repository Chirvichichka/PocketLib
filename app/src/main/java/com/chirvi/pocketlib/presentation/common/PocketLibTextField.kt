package com.chirvi.pocketlib.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun PocketLibTextField(
    text: String,
    placeHolderText: String,
    leadingIconId: Int,
    onValueChange: (String) -> Unit
) {
    val textStyle = PocketLibTheme.textStyles.normalStyle.copy(
        color = PocketLibTheme.colors.dark
    )

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeHolderText,
                style = textStyle
            )
        },
        textStyle = textStyle,
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIconId),
                contentDescription = null,
                tint = PocketLibTheme.colors.dark
            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = PocketLibTheme.colors.secondary,
            unfocusedContainerColor = PocketLibTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = PocketLibTheme.colors.dark
        )
    )
}