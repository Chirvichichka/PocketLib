package com.chirvi.pocketlib.presentation.ui.screen.book_add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun AddBookScreen() {
    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        navigationIcon = {
             IconButton(
                 onClick = { /*TODO*/ }
             ) {
                Icon(
                    painter = painterResource(id = R.drawable.home_icon), // Todo Поменять иконку
                    contentDescription = null
                )
             }
        },
        title = {
            Text(
                text = "Добавить книгу", // Todo перевод
                style = PocketLibTheme.textStyles.primaryLarge.copy(
                    color = PocketLibTheme.colors.primary
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PocketLibTheme.colors.tertiary
        )
    )
}