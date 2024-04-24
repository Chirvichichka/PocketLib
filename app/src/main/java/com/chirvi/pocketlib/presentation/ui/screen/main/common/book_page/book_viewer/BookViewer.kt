package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page.book_viewer

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import kotlinx.coroutines.launch

@Composable
fun BookViewer(id: String) {
    val viewModel = hiltViewModel<BookViewerViewModel>()
    viewModel.downloadBook(id)
    val state by viewModel.state.observeAsState(BookViewerState.Initial)

    val coroutineScope = rememberCoroutineScope()
    val scrollState by viewModel.scrollState.observeAsState(rememberScrollState())

    Scaffold(
        floatingActionButton = {
            if (state == BookViewerState.Content) {
                SmallFloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.animateScrollToTop(scrollState)
                        }
                    },
                    containerColor = PocketLibTheme.colors.tertiary,
                    contentColor = PocketLibTheme.colors.onTertiary,
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_up),
                        contentDescription = null
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PocketLibTheme.colors.background)
                .padding(paddingValues)
        ) {
            when(state) {
                BookViewerState.Initial -> { LoadingCircle() }
                BookViewerState.Content -> { Content(viewModel = viewModel, scrollState = scrollState) }
                BookViewerState.Loading -> { LoadingCircle() }
            }
        }
    }
}

@Composable
private fun Content(
    viewModel: BookViewerViewModel,
    scrollState: ScrollState
) {
    val text by viewModel.text.observeAsState(emptyList())
    val currentChapter by viewModel.currentChapter.observeAsState(0)
    
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    viewModel.offsetXChange(delta)
                    viewModel.drag()
                }
            ),
    ) {
        Text(
            text = text[currentChapter],
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.onBackground
            )
        )
    }
}

@Composable
private fun ButtonToUp() {

}