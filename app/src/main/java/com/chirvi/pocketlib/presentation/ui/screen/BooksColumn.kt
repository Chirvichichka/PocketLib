package com.chirvi.pocketlib.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun FavoriteBooks() {
    LazyColumn(

    ) {
        items ( 10 ) {
            VerticalBookCard()
        }
    }
}

@Composable
fun VerticalBookCard() {
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
    ) {
        Image(
            modifier = Modifier
                .size(
                    height = 150.dp,
                    width = 100.dp
                ),
            painter = painterResource(id =  R.drawable.test_image),
            contentDescription = null
        )
        Column(

        ) {
            Text(
                text = "Название",
                style = PocketLibTheme.textStyles.primary
            )
            Text(
                text = "Автор",
                style = PocketLibTheme.textStyles.primarySmall
            )
        }
    }
}