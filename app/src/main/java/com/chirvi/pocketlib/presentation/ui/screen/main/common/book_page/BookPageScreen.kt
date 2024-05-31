package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.BackButton
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonIconFavorite
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun BookPageScreen(
    idPost: String,
) {
    val viewModel = hiltViewModel<BookPageViewModel>()
    val state by viewModel.state.observeAsState(BookPageState.Initial)
    viewModel.getBookById(id = idPost)
    val book by viewModel.book.observeAsState(BookPresentation())

    val navigation = LocalNavigationState.current
    val currentRoute = navigation.navHostController.currentDestination?.route

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
    ) {
        BookPageAppTopBar(
            navigateToBack = {
                viewModel.navigateToBack(
                    navigation = navigation,
                    currentRoute = currentRoute?:""
                )
            },
            bookName = book.name
        )
        when(state) {
            BookPageState.Content -> {
                Content(
                    book = book,
                    navigateToBookViewer = {
                        viewModel.navigateToBookViewer(
                            navigation = navigation,
                            currentRoute = currentRoute?:""
                        )
                    }
                )
            }
            BookPageState.Initial -> { LoadingCircle() }
            BookPageState.Loading -> { LoadingCircle() }
        }
    }
}

@Composable
private fun Content(
    book: BookPresentation,
    navigateToBookViewer: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Poster(image = book.image)
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            if (book.bookFile != null) {
                ButtonWithText(
                    text = stringResource(id = R.string.read),
                    onClickListener = { navigateToBookViewer() }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextInfo(book = book)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookPageAppTopBar(
    bookName: String,
    navigateToBack: () -> Unit
) {
    PocketLibTopAppBar(
        title = {
            Text(
                text = bookName,
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.onSecondaryContainer
                )
            )
        },
        navigationIcon = {
            BackButton(
                onClickListener = { navigateToBack() }
            )
        },
        actions = {
            ButtonIconFavorite {
                //todo
            }
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Poster(
    image: Uri?
) {
    GlideImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(
                color = PocketLibTheme.colors.onSurface
            ),
        model = image,
        loading = placeholder(R.drawable.default_book),
        contentDescription = null,
        contentScale = ContentScale.Fit
    ){
        it.error(R.drawable.default_book)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .load(image)
    }
}

@Composable
private fun TextInfo(
    book: BookPresentation
) {
    val genres = if(book.genres != emptyList<String>()) {
        book.genres.joinToString(", ")
    } else {
        ""
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = book.name,
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.onBackground
            )
        )
        Text(
            text = book.author,
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.onBackground
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.description,
            style = PocketLibTheme.textStyles.smallStyle.copy(
                color = PocketLibTheme.colors.onBackground
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = genres,
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.onBackground
            )
        )
    }
}

