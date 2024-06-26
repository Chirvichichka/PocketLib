package com.chirvi.pocketlib.presentation.ui.common.book_card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonIconFavorite
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VerticalBookCard(  //todo ПЕРЕДЕЛАТЬ
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
            GlideImage(
                modifier = Modifier
                    .height(150.dp)
                    .padding(
                        top = 8.dp,
                        end = 8.dp,
                        start = 8.dp
                    )
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                model = book.image,
                loading = placeholder(R.drawable.default_book),
                contentDescription = null
            ){
                it.error(R.drawable.default_book)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load(book.image)
            }
        }
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier.padding(all = 8.dp).weight(1f)
            ) {
                Text(
                    text = book.name,
                    style = PocketLibTheme.textStyles.largeStyle.copy(
                        color = PocketLibTheme.colors.onSecondaryContainer
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = book.author,
                    style = PocketLibTheme.textStyles.normalStyle.copy(
                        color = PocketLibTheme.colors.onSecondaryContainer
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}