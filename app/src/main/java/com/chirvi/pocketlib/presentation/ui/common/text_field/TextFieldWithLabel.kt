package com.chirvi.pocketlib.presentation.ui.common.text_field

import android.os.Message
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import java.lang.Error

@Composable
fun TextFieldWithLabel(
    modifier: Modifier = Modifier,
    text: String,
    textLabel: String,
    singleLine: Boolean = true,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange(it) },
        singleLine = singleLine,
        label = {
            Text(
                text = textLabel,
                style = PocketLibTheme.textStyles.normalStyle
            )
        },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        textStyle = PocketLibTheme.textStyles.normalStyle,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = PocketLibTheme.colors.onBackground,

            focusedContainerColor = PocketLibTheme.colors.surfaceVariant,
            focusedIndicatorColor = PocketLibTheme.colors.primary,
            focusedTextColor = PocketLibTheme.colors.onPrimaryContainer,
            focusedLabelColor = PocketLibTheme.colors.primary,

            unfocusedContainerColor = PocketLibTheme.colors.surfaceVariant,
            unfocusedIndicatorColor = PocketLibTheme.colors.onPrimaryContainer,
            unfocusedTextColor = PocketLibTheme.colors.onPrimaryContainer,
            unfocusedLabelColor = PocketLibTheme.colors.onPrimaryContainer,

            errorContainerColor = PocketLibTheme.colors.surfaceVariant,
            errorIndicatorColor = PocketLibTheme.colors.errorContainer,
            errorTextColor = PocketLibTheme.colors.onPrimaryContainer,
            errorLabelColor = PocketLibTheme.colors.primary,
            errorCursorColor = PocketLibTheme.colors.onBackground,
        )
    )

}