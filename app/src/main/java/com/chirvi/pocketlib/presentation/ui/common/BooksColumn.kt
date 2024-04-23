package com.chirvi.pocketlib.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chirvi.domain.models.DisplayMode
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.ui.common.book_card.HorizontalBookCard
import com.chirvi.pocketlib.presentation.ui.common.book_card.VerticalBookCard

@Composable
fun BookColumn(
    books: List<BookPresentation> = emptyList(),
    displayMode: DisplayMode,
    navigateToPost: (String) -> Unit
) {
    when(displayMode.name) {
        DisplayMode.LIST.name -> {
            ListDisplay(
                books = books,
                navigateToPost = navigateToPost
            )
        }
        DisplayMode.GRID.name -> {
            GridDisplay(
                books = books,
                navigateToPost = navigateToPost
            )
        }
    }
}

@Composable
private fun ListDisplay(
    books: List<BookPresentation>,
    navigateToPost: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(books) { book ->
            VerticalBookCard(
                book = book,
                onClickPreview = { navigateToPost(book.id) }
            )
        }
    }
}

@Composable
private fun GridDisplay(
    books: List<BookPresentation>,
    navigateToPost: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.fillMaxWidth()
    ){
        items ( books ) {book ->
            HorizontalBookCard(
                book = book,
                onClickPreview = { navigateToPost(book.id) }
            )
        }
    }
}

