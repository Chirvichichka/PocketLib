package com.chirvi.pocketlib.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.chirvi.pocketlib.presentation.models.Book
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun VerticalBookCard(
    book: Book
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.secondary
        ),
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(
                    height = 150.dp,
                    width = 100.dp
                )
                .padding(
                    top = 8.dp,
                    end = 8.dp,
                    start = 8.dp
                )
                .clip(
                    RoundedCornerShape(
                        4.dp,
                        4.dp
                    )
                ),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = book.posterId),
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Text(
                text = book.name,
                style = PocketLibTheme.textStyles.primaryLarge.copy(
                    color = PocketLibTheme.colors.secondaryText
                )
            )
            Text(
                text = book.author,
                style = PocketLibTheme.textStyles.primarySmall.copy(
                    color = PocketLibTheme.colors.secondaryText
                )
            )
        }
    }
}