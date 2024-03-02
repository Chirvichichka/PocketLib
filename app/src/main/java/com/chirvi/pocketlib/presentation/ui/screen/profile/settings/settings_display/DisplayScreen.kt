package com.chirvi.pocketlib.presentation.ui.screen.profile.settings.settings_display

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun DisplayScreen() {
    val viewModel = hiltViewModel<DisplayViewModel>()

    val feedCheckBoxState by viewModel.feedSwitchState.observeAsState(false)
    val myBooksCheckBoxState by viewModel.myBooksCSwitchState.observeAsState(false)
    val favoriteCheckBoxState by viewModel.favoriteSwitchState.observeAsState(false)

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(id = R.string.card_display),
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.dark,
                fontWeight = FontWeight.Bold
            )
        )
        RowSwitch(
            textId = R.string.feed_switch,
            state = feedCheckBoxState,
            onClickListener = { viewModel.feedSwitchStateChanged() }
        )
        RowSwitch(
            textId = R.string.my_books_switch,
            state = myBooksCheckBoxState,
            onClickListener = { viewModel.myBooksSwitchStateChanged() }
        )
        RowSwitch(
            textId = R.string.favorite_switch,
            state = favoriteCheckBoxState,
            onClickListener = { viewModel.favoriteSwitchStateChanged() }
        )
    }
}

@Composable
private fun RowSwitch(
    textId: Int,
    state: Boolean,
    onClickListener: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.7f),
            text = stringResource(id = textId),
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.dark
            )
        )
        Switch(
            checked = state,
            onCheckedChange = { onClickListener() },
            colors = SwitchDefaults.colors(
                checkedTrackColor = PocketLibTheme.colors.tertiary,
                uncheckedTrackColor = PocketLibTheme.colors.dark,
                uncheckedBorderColor = PocketLibTheme.colors.dark,
                uncheckedThumbColor = PocketLibTheme.colors.primary
            )
        )
    }
}