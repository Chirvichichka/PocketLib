package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import kotlinx.coroutines.launch

@Composable
fun BookViewer(id: String) {
    val viewModel = hiltViewModel<BookViewerViewModel>()
    viewModel.downloadBook(id)


    val text by viewModel.text.observeAsState(emptyList())
    val currentChapter by viewModel.currentChapter.observeAsState(0)

    val coroutineScope = rememberCoroutineScope()
    val scrollState by viewModel.scrollState.observeAsState(rememberScrollState())


    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.animateScrollToTop(scrollState)
                    }
                },
                shape = CircleShape
            ) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Scroll to top")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PocketLibTheme.colors.background)
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        viewModel.offsetXChange(delta)
                        viewModel.drag()
                    }
                ),
        ) {
            if(text.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxSize()

                ) {
                    Text(
                        text = text[currentChapter],
                        style = PocketLibTheme.textStyles.largeStyle.copy(
                            color = PocketLibTheme.colors.onBackground
                        )
                    )
                }
            }

        }
    }
}