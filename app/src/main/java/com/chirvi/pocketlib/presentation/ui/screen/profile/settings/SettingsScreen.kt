package com.chirvi.pocketlib.presentation.ui.screen.profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.common.BackButton
import com.chirvi.pocketlib.presentation.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun SettingsScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = hiltViewModel<SettingsViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.primary)
    ) {
        SettingsAppBar(
            onBackPressed = onBackPressed
        )
        DisplayBooks(
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsAppBar(
    onBackPressed: () -> Unit
) {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.settings),
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.primary,
                )
            )
        },
        navigationIcon = {
            BackButton(
                onClickListener = onBackPressed
            )
        }
    )
}

@Composable
private fun DisplayBooks(
   viewModel: SettingsViewModel
) {
    val feedCheckBoxState by viewModel.feedCheckBoxState.observeAsState(false)

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        RowCheckBox(
            textId = R.string.feed_checkbox,
            state = feedCheckBoxState,
            onClickListener = { viewModel.feedCheckBoxStateChanged() }
        )
        RowCheckBox(
            textId = R.string.feed_checkbox,
            state = feedCheckBoxState,
            onClickListener = { viewModel.feedCheckBoxStateChanged() }
        )
        RowCheckBox(
            textId = R.string.feed_checkbox,
            state = feedCheckBoxState,
            onClickListener = { viewModel.feedCheckBoxStateChanged() }
        )
    }

}

@Composable
private fun RowCheckBox(
    textId: Int,
    state: Boolean,
    onClickListener: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = stringResource(id = textId),
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.black
            )
        )
        Checkbox(
            checked = state,
            onCheckedChange = { onClickListener() },
            colors = CheckboxDefaults.colors(
                checkedColor = PocketLibTheme.colors.tertiary
            )
        )
    }
}