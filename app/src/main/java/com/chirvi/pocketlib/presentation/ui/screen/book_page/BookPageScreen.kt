package com.chirvi.pocketlib.presentation.ui.screen.book_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun BookPageScreen(
    onBackPressed: () -> Unit
) {
    Scaffold(
        containerColor = PocketLibTheme.colors.primary,
        topBar = {  }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FeedAppTopBar(onBackPressed = onBackPressed)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedAppTopBar(
    onBackPressed: () -> Unit
) {
    PocketLibTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackPressed()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null
                )
            }
        }
    )
}