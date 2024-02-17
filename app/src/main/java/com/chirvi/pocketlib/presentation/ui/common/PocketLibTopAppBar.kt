package com.chirvi.pocketlib.presentation.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketLibTopAppBar(
    scroll: TopAppBarScrollBehavior? = null,
    title: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = PocketLibTheme.colors.tertiary,
            scrolledContainerColor = PocketLibTheme.colors.tertiary
        ),
        title = { title() },
        actions = { actions() },
        navigationIcon = { navigationIcon() },
        scrollBehavior = scroll
    )
}