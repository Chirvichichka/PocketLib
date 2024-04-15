package com.chirvi.pocketlib.presentation.ui.common.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun TextFieldPassword(
    text: String,
    textLabel: String,
    onValueChange: (String) -> Unit,
) {
    var checkPassword by remember { mutableStateOf(true) } //todo перенести во вью модель

    OutlinedTextField(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        label = {
            Text(
                text = textLabel,
                style = PocketLibTheme.textStyles.normalStyle
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { checkPassword = !checkPassword }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.eye),
                    contentDescription = null,
                    tint = PocketLibTheme.colors.onBackground
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        visualTransformation = if (checkPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
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
        )
    )
}