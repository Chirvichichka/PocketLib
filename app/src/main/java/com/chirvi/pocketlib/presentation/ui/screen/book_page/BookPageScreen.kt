package com.chirvi.pocketlib.presentation.ui.screen.book_page

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.BackButton
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonIconFavorite
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun BookPageScreen(
 //   book: BookPresentation,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.primary)
    ) {
        FeedAppTopBar( onBackPressed = onBackPressed)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Poster()
            Column(
                modifier = Modifier.padding(all = 16.dp)
            ) {
                ButtonWithText(text = stringResource(id = R.string.read)) {}
                Spacer(modifier = Modifier.height(16.dp))
            //    TextInfo(book = book)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedAppTopBar(
  //  book: BookPresentation,
    onBackPressed: () -> Unit
) {
    PocketLibTopAppBar(
        title = {
          Text(
              text = "book.name,",
              style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                  color = PocketLibTheme.colors.primary
              )
          )
        },
        navigationIcon = {
            BackButton(
                onClickListener = onBackPressed
            )
        },
        actions = {
            ButtonIconFavorite {
                //todo
            }
        }
    )
}

@Composable
private fun Poster() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = PocketLibTheme.colors.dark),
        painter = painterResource(id = R.drawable.test_image),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun TextInfo(
    book: BookPresentation
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
            text = text,
            style = PocketLibTheme.textStyles.smallStyle.copy(
                color = PocketLibTheme.colors.dark
            )
        )
    }
}