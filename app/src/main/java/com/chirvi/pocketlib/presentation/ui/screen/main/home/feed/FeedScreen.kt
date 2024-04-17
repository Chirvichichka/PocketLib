package com.chirvi.pocketlib.presentation.ui.screen.main.home.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.domain.models.DisplayMode
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.ui.common.BookColumn
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    onClickPreview: (String) -> Unit,
    scroll: TopAppBarScrollBehavior,
) {
    val viewModel = hiltViewModel<FeedViewModel>()
    val state by viewModel.state.observeAsState(FeedState.Initial)
    val books by viewModel.postsList.observeAsState(emptyList())
    val displayMode by viewModel.feedDisplayMode.observeAsState(DisplayMode.LIST)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
    ) {
        FeedTopAppBar(
            viewModel = viewModel,
            scroll = scroll,
        )
        when(state) {
            FeedState.Initial -> {  }
            FeedState.Content -> {
                Content(
                    displayMode = displayMode,
                    books = books,
                    onClickPreview = onClickPreview
                )
            }
            FeedState.Loading -> { LoadingCircle() }
        }
    }
}

@Composable
private fun Content(
    displayMode: DisplayMode,
    books: List<BookPresentation>,
    onClickPreview: (String) -> Unit
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
        actions = {
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
        color = PocketLibTheme.colors.onBackground
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 5.dp
            ),
        value = text,
        onValueChange = { viewModel.textChange(text = it) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_book),
                style = textStyle
            )
        },
        textStyle = textStyle,
        singleLine = true,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                tint = PocketLibTheme.colors.onBackground
            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = PocketLibTheme.colors.secondary,

            focusedContainerColor = PocketLibTheme.colors.secondaryContainer,
            focusedTextColor = PocketLibTheme.colors.onBackground,
            focusedIndicatorColor = Color.Transparent,

            unfocusedContainerColor = PocketLibTheme.colors.secondaryContainer,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = PocketLibTheme.colors.onBackground,
        )
    )
}