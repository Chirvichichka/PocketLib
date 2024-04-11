package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chirvi.domain.models.DisplayMode
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.navigation.item.ProfileTabRowItem
import com.chirvi.pocketlib.presentation.ui.common.BookColumn
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.screen.main.home.feed.FeedState
import com.chirvi.pocketlib.presentation.ui.screen.main.localFirebaseUser
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun UserScreen(
    onClickPreview: (String) -> Unit,
    onClickSettings: () -> Unit,
    onClickEdit: () -> Unit,
) {
    val viewModel = hiltViewModel<UserViewModel>()
    val image by viewModel.image.observeAsState(Uri.EMPTY)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
    ) {
        ProfileTopAppBar(
            onClickSettings = onClickSettings
        )
        UserInfo(onClickListener = onClickEdit, image = image)
        ProfileTabRow(
            viewModel = viewModel,
            onClickPreview = onClickPreview
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTopAppBar(
    onClickSettings: () -> Unit
) {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile),
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.onSecondaryContainer,
                )
            )
        },
        actions = {
            IconButton(
                onClick = { onClickSettings() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = null,
                    tint = PocketLibTheme.colors.onSecondaryContainer
                )
            }
        }
    )
}

@Composable
private fun UserInfo(
    image: Uri?,
    onClickListener: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(all = 8.dp,)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = CircleShape),
            )
//            Image(
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(shape = CircleShape),
//                painter = painterResource(id = R.drawable.test_image),
//                contentScale = ContentScale.Crop,
//                contentDescription = null,
//            )
            Column(
                modifier = Modifier
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = localFirebaseUser.current?.uid.toString(),
                    style = PocketLibTheme.textStyles.largeStyle.copy(
                        color = PocketLibTheme.colors.onBackground
                    )
                )
                Text(
                    text = localFirebaseUser.current?.email.toString(),
                    style = PocketLibTheme.textStyles.normalStyle.copy(
                        color = PocketLibTheme.colors.onBackground
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ButtonWithText(
            text = stringResource(id = R.string.edit),
            onClickListener = { onClickListener() }
        )
    }
}

@Composable
private fun ProfileTabRow(
    viewModel: UserViewModel,
    onClickPreview: (String) -> Unit
) {
    val tabRowIndex by viewModel.tabRowItem.observeAsState(0)

    val books by viewModel.postsList.observeAsState(emptyList())
    val state by viewModel.state.observeAsState(UserPostState.Initial)
    val myBooksDisplayMode by viewModel.myBooksDisplayMode.observeAsState(DisplayMode.LIST)
    val favoritesDisplayMode by viewModel.favoritesDisplayMode.observeAsState(DisplayMode.LIST)

    val items = listOf(
        ProfileTabRowItem.MyBooks,
        ProfileTabRowItem.Favorite
    )

    TabRow(
        containerColor = PocketLibTheme.colors.background,
        selectedTabIndex = tabRowIndex,
        contentColor = PocketLibTheme.colors.onBackground,
        indicator = { tabPosition ->
            SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPosition[tabRowIndex]),
                color = PocketLibTheme.colors.primary
            )
        }
    ) {
        items.forEachIndexed { index, item  ->
            LeadingIconTab(
                text = {
                    Text(
                        text = stringResource(id = item.title)
                    )
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        tint = PocketLibTheme.colors.primary,
                        painter = painterResource(id = item.iconId),
                        contentDescription = null
                    )
                },
                selected = index == tabRowIndex,
                onClick = { viewModel.onIndexChange(index = index) }
            )
        }
    }

    when(tabRowIndex) {
        0 -> {
            when(state) {
                UserPostState.Initial -> {  }
                UserPostState.Loading -> { LoadingCircle() }
                UserPostState.Content -> {
                    BookColumn(
                        displayMode = myBooksDisplayMode,
                        books = books,
                        onClickPreview = onClickPreview
                    )
                }
            }
        }
        1 -> {
            BookColumn(
                displayMode = favoritesDisplayMode,
                onClickPreview = onClickPreview
            )
        }
    }
}