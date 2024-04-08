package com.chirvi.pocketlib.presentation.ui.common

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun CustomSwitch(
    state: Boolean,
    onClickListener: () -> Unit,
) {
    Switch(
        checked = state,
        onCheckedChange = { onClickListener() },
        colors = SwitchDefaults.colors(
            checkedTrackColor = PocketLibTheme.colors.primary,
            checkedBorderColor = PocketLibTheme.colors.primary,
            checkedThumbColor = PocketLibTheme.colors.onPrimary,

            uncheckedTrackColor = PocketLibTheme.colors.onSecondary,
            uncheckedBorderColor = PocketLibTheme.colors.onSecondaryContainer,
            uncheckedThumbColor = PocketLibTheme.colors.onSecondaryContainer
        )
    )
}