package com.chirvi.pocketlib.presentation.ui.common.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun BackButton(
    onClickListener: () -> Unit
) {
    IconButton(
        onClick = {
            onClickListener()
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = null,
            tint = PocketLibTheme.colors.primary
        )
    }
}