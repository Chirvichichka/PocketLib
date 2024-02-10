package com.chirvi.pocketlib.presentation.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.common.BookColumn
import com.chirvi.pocketlib.presentation.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.common.VerticalBookCard
import com.chirvi.pocketlib.presentation.models.Book
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()

    val books = mutableListOf<Book>().apply {
        repeat(21) {
            add(
                Book(
                    id = it
                )
            )
        }
    }

    Scaffold(
        topBar = { HomeTopAppBar(viewModel = viewModel) }
    ) { paddingValues ->
        BookColumn(
            grid = false,
            paddingValues = paddingValues,
            book = books[0]
        )
    }
}

@Composable
private fun HomeTopAppBar(
    viewModel: HomeViewModel
) {
    val isGrid by viewModel.isGrid.observeAsState(false)

    val tint = if (isGrid) {
        PocketLibTheme.colors.secondary
    } else {
        PocketLibTheme.colors.black
    }

    PocketLibTopAppBar(
        title = {
            SearchBook(viewModel = viewModel)
        },
        actions = {
            IconButton(
                onClick = {
                    viewModel.gridChange()
                }
            ) {
                Icon(
                    painter =  painterResource(id = R.drawable.filter),
                    contentDescription = null,
                    tint = tint
                )
            }
        }
    )
}

@Composable
private fun SearchBook(
    viewModel: HomeViewModel
) {
    val text by viewModel.newText.observeAsState("")
    val textStyle = PocketLibTheme.textStyles.primarySmall.copy(
        color = PocketLibTheme.colors.black
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(48.dp),
        value = text,
        onValueChange = { viewModel.textChange(text = it) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_book),
                style = textStyle.copy(
                    fontStyle = FontStyle.Italic
                )
            )
        },
        textStyle = textStyle,
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                tint = PocketLibTheme.colors.black
            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = PocketLibTheme.colors.secondary,
            unfocusedContainerColor = PocketLibTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = PocketLibTheme.colors.black
        )
    )
}