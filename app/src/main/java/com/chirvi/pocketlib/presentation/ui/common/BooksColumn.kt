package com.chirvi.pocketlib.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chirvi.pocketlib.presentation.ui.common.book_card.HorizontalBookCard
import com.chirvi.pocketlib.presentation.ui.common.book_card.VerticalBookCard
import com.chirvi.pocketlib.presentation.models.Book

@Composable
fun BookColumn(
    book: Book,
    grid: Boolean = true,
    count: Int = 30,
    onClickPreview: () -> Unit = {}
) {
    if (grid) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items( count ) {
                VerticalBookCard(
                    book = book,
                    onClickPreview = onClickPreview
                )
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxWidth()
        ){
            items ( count ) {
                HorizontalBookCard(
                    book = book,
                    onClickPreview = onClickPreview
                )
            }
        }
    }
}
