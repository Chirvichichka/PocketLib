package com.chirvi.pocketlib.presentation.ui.common.book_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.models.Book
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun VerticalBookCard(
    book: Book,
    onClickPreview: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.secondary
        ),
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
            .clickable {
                onClickPreview()
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(150.dp,)
                    .padding(
                        top = 8.dp,
                        end = 8.dp,
                        start = 8.dp
                    )
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = book.posterId),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Text(
                text = book.name,
                style = PocketLibTheme.textStyles.largeStyle.copy(
                    color = PocketLibTheme.colors.dark
                )
            )
            Text(
                text = book.author,
                style = PocketLibTheme.textStyles.normalStyle.copy(
                    color = PocketLibTheme.colors.dark
                )
            )
        }
    }
}