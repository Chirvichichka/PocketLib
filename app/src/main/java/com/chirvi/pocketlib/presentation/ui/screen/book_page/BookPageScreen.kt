package com.chirvi.pocketlib.presentation.ui.screen.book_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.models.Book
import com.chirvi.pocketlib.presentation.ui.common.BackButton
import com.chirvi.pocketlib.presentation.ui.common.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun BookPageScreen(
    book: Book = Book(),
    onBackPressed: () -> Unit
) {
    val viewModel = hiltViewModel<BookPageViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.primary)
    ) {
        FeedAppTopBar(onBackPressed = onBackPressed)
        Poster()
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            ButtonWithText(text = "Читать") {}
            Spacer(modifier = Modifier.height(16.dp))
            TextInfo(book = book)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedAppTopBar(
    onBackPressed: () -> Unit
) {
    PocketLibTopAppBar(
        navigationIcon = {
            BackButton(
                onClickListener = onBackPressed
            )
        },
        actions = {
            IconButton(
                onClick = {
                    /*TODO*/
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun Poster() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = PocketLibTheme.colors.quaternary),
        painter = painterResource(id = R.drawable.test_image),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun TextInfo(
    book: Book
) {
    Column(
    ) {
        Text(
            text = book.name,
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.dark
            )
        )
        Text(
            text = book.author,
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.dark
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam at neque sem. Sed placerat vitae massa ac consequat. Pellentesque vehicula orci in justo ultricies suscipit. Donec vehicula neque in justo feugiat placerat. Nam pharetra dolor felis, quis varius neque interdum viverra. Cras quis dolor bibendum, vehicula augue non, molestie leo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur nec libero vel neque pellentesque congue. Vivamus placerat feugiat faucibus. Nam non rutrum dolor. Curabitur quam tellus, pretium vitae convallis vel, tempor vitae ex. Sed in quam risus. Nam rhoncus velit et risus aliquet consectetur. Nam."
        Text(
            overflow = TextOverflow.Ellipsis,
            maxLines = 5,
            text = text,
            style = PocketLibTheme.textStyles.smallStyle.copy(
                color = PocketLibTheme.colors.quaternary
            )
        )
    }
}