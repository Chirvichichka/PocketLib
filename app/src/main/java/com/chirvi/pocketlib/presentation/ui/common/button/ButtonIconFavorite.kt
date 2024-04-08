package com.chirvi.pocketlib.presentation.ui.common.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun ButtonIconFavorite(
    onClickListener: () -> Unit
) {
    IconButton(
        onClick = {
            onClickListener()
        }
    ) {
        Icon(
            tint = PocketLibTheme.colors.onBackground,
            painter = painterResource(id = R.drawable.bookmark_border),
            contentDescription = null
        )
    }
}