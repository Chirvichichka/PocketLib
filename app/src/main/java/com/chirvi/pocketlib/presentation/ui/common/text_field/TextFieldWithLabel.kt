package com.chirvi.pocketlib.presentation.ui.common.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun TextFieldWithLabel(
    text: String,
    textLabel: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        label = {
            Text(
                text = textLabel,
                style = PocketLibTheme.textStyles.normalStyle.copy(
                    fontStyle = FontStyle.Italic
                )
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        textStyle = PocketLibTheme.textStyles.normalStyle,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = PocketLibTheme.colors.dark,

            focusedContainerColor = PocketLibTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = PocketLibTheme.colors.dark,
            focusedLabelColor = PocketLibTheme.colors.dark,

            unfocusedContainerColor = PocketLibTheme.colors.secondary,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = PocketLibTheme.colors.dark,
            unfocusedLabelColor = PocketLibTheme.colors.dark,
        )
    )
}