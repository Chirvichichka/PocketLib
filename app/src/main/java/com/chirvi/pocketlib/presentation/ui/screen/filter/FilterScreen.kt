package com.chirvi.pocketlib.presentation.ui.screen.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.BackButton
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun FilterScreen(
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.primary)
    ) {
        FilterTopAppBar(
            onBackPressed = onBackPressed
        )
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Text(
                text = "SOON",
                style = TextStyle(
                    fontSize = 48.sp
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterTopAppBar(
    onBackPressed: () -> Unit
) {
    PocketLibTopAppBar(
        title = {
            Text(
               text = stringResource(id = R.string.filter),
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.primary
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