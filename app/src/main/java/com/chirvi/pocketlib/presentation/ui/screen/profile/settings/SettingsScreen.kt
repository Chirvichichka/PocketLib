package com.chirvi.pocketlib.presentation.ui.screen.profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.primary)
    ) {
        SettingsAppBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsAppBar() {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.settings),
                style = PocketLibTheme.textStyles.primaryLarge.copy(
                    color = PocketLibTheme.colors.black,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    )
}