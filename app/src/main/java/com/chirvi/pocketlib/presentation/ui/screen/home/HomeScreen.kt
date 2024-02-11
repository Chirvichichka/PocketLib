package com.chirvi.pocketlib.presentation.ui.screen.home

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.presentation.navigation.HomeScreenState
import com.chirvi.pocketlib.presentation.ui.screen.feed.FeedScreen
import com.chirvi.pocketlib.presentation.ui.screen.book_page.BookPageScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    scroll: TopAppBarScrollBehavior,
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)
    val currentState = screenState.value

    when(currentState) {
        is HomeScreenState.Feed -> {
            FeedScreen(
                scroll = scroll,
                onClickPreview = { viewModel.showPost() }
            )
        }
        is HomeScreenState.Post -> {
            BookPageScreen(
                onBackPressed = {
                    viewModel.closePost()
                }
            )
            BackHandler {
                viewModel.closePost()
            }
        }
        is HomeScreenState.Initial -> {}
    }
}

