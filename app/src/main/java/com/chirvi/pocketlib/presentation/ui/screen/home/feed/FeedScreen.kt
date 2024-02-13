package com.chirvi.pocketlib.presentation.ui.screen.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.common.BookColumn
import com.chirvi.pocketlib.presentation.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.models.Book
import com.chirvi.pocketlib.presentation.navigation.state.FeedScreenState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    onClickPreview: () -> Unit,
    scroll: TopAppBarScrollBehavior
) {
    val viewModel = hiltViewModel<FeedViewModel>()
    val books = mutableListOf<Book>().apply {
        repeat(21) { add(Book(id = it)) }
    }
    val currentState by viewModel.screenState.observeAsState()

    when( currentState ) {
        is FeedScreenState.Feed -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = PocketLibTheme.colors.primary)
            ) {
                FeedTopAppBar(
                    viewModel = viewModel,
                    scroll = scroll
                )
                BookColumn(
                    grid = false,
                    book = books[0],
                    onClickPreview = onClickPreview
                )
            }
        }
        FeedScreenState.Initial -> {}
        null -> TODO()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedTopAppBar(
    viewModel: FeedViewModel,
    scroll: TopAppBarScrollBehavior
) {
    val isGrid by viewModel.isGrid.observeAsState(false)

    val tint = if (isGrid) {
        PocketLibTheme.colors.secondary
    } else {
        PocketLibTheme.colors.black
    }

    PocketLibTopAppBar(
        scroll = scroll,
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
    viewModel: FeedViewModel
) {
    val text by viewModel.newText.observeAsState("")
    val textStyle = PocketLibTheme.textStyles.primary.copy(
        color = PocketLibTheme.colors.black
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(51.dp),
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