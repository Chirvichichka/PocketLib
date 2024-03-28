package com.chirvi.pocketlib.presentation.ui.screen.main.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.domain.models.DisplayMode
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.ui.common.BookColumn
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    onClickPreview: () -> Unit,
    scroll: TopAppBarScrollBehavior,
) {
    val viewModel = hiltViewModel<FeedViewModel>()
    val state by viewModel.state.observeAsState(FeedState.Initial)
    val books by viewModel.postsList.observeAsState(emptyList())
    val displayMode by viewModel.feedDisplayMode.observeAsState(DisplayMode.LIST)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.primary)
    ) {
        FeedTopAppBar(
            viewModel = viewModel,
            scroll = scroll,
        )
        ButtonWithText(text = "load data") {
            viewModel.loadData()
        }
        when(state) {
            FeedState.Initial -> {  }
            FeedState.Content -> {
                Content(
                    displayMode = displayMode,
                    books = books,
                    onClickPreview = { onClickPreview() }
                )
            }
            FeedState.Loading -> { CircularProgressIndicator() }
        }
    }
}


@Composable
private fun Content(
    displayMode: DisplayMode,
    books: List<BookPresentation>,
    onClickPreview: () -> Unit
) {
    BookColumn(
        displayMode = displayMode,
        books = books,
        onClickPreview = onClickPreview
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedTopAppBar(
    viewModel: FeedViewModel,
    scroll: TopAppBarScrollBehavior,
) {
    PocketLibTopAppBar(
        scroll = scroll,
        title = {
            SearchBook(viewModel = viewModel)
        },
    )
}

@Composable
private fun SearchBook(
    viewModel: FeedViewModel
) {
    val text by viewModel.newText.observeAsState("")
    val textStyle = PocketLibTheme.textStyles.normalStyle.copy(
        color = PocketLibTheme.colors.dark
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
                tint = PocketLibTheme.colors.dark
            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = PocketLibTheme.colors.secondary,
            unfocusedContainerColor = PocketLibTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = PocketLibTheme.colors.dark
        )
    )
}