package com.chirvi.pocketlib.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.models.Book

@Composable
fun BookColumn(
    book: Book,
    grid: Boolean = true,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    if(grid) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items( 30 ) {
                VerticalBookCard(
                    book = book
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ){
            items ( 30 ) {
                HorizontalBookCard()
            }
        }
    }
}

