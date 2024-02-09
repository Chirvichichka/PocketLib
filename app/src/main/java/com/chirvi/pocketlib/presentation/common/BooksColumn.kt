package com.chirvi.pocketlib.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun BookColumn() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ){
        items ( 10 ) {
            VerticalBookCard()
        }
    }
}

@Composable
fun VerticalBookCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.secondary
        ),
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
            .fillMaxWidth()
    ) {
        Row {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .size(
                        height = 150.dp,
                        width = 100.dp
                    )
                    .padding(8.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 4.dp,
                            bottomStart = 4.dp
                        )
                    ),
                contentScale = ContentScale.Crop,
                painter = painterResource(id =  R.drawable.test_image),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Название",
                    style = PocketLibTheme.textStyles.primaryLarge
                )
                Text(
                    text = "Автор",
                    style = PocketLibTheme.textStyles.primary
                )
            }
        }
    }
}