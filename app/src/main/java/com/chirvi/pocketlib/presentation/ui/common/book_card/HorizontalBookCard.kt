package com.chirvi.pocketlib.presentation.ui.common.book_card

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonIconFavorite
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun HorizontalBookCard( //todo ПЕРЕДЕЛАТЬ
    book: BookPresentation,
    onClickPreview: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.secondaryContainer
        ),
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 6.dp
            )
            .fillMaxWidth()
            .clickable { onClickPreview() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .size(
                        height = 130.dp,
                        width = 80.dp
                    )
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                painter = if(book.image != Uri.EMPTY) {
                    rememberAsyncImagePainter(book.image)
                } else {
                    painterResource(id = R.drawable.default_book)
                },
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Row {
                    Column(
                        modifier = Modifier.weight(1f)
                    ){
                        Text(
                            text = book.name,
                            style = PocketLibTheme.textStyles.largeStyle.copy(
                                color = PocketLibTheme.colors.onSecondaryContainer
                            )
                        )
                        Text(
                            text = book.author,
                            style = PocketLibTheme.textStyles.normalStyle.copy(
                                color = PocketLibTheme.colors.onSecondaryContainer
                            )
                        )
                    }
                    ButtonIconFavorite(
                        onClickListener = {
                            //todo
                        }
                    )
                }
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5,
                    text = book.description,
                    style = PocketLibTheme.textStyles.smallStyle.copy(
                        color = PocketLibTheme.colors.onSecondaryContainer,
                        textAlign = TextAlign.Justify
                    )
                )
            }
        }
    }
}


