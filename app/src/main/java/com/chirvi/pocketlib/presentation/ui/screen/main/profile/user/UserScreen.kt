package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chirvi.domain.models.DisplayMode
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.navigation.item.ProfileTabRowItem
import com.chirvi.pocketlib.presentation.ui.common.BookColumn
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.LocalUser
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun UserScreen() {
    val viewModel = hiltViewModel<UserViewModel>()
    val navigation = LocalNavigationState.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
    ) {
        ProfileTopAppBar { viewModel.navigateToSettings(navigation) }
        UserInfo()
        ProfileTabRow(viewModel = viewModel,)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTopAppBar(
    navigateToSettings: () -> Unit
) {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile),
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.onBackground,
                )
            )
        },
        actions = {
            IconButton(
                onClick = { navigateToSettings() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = null,
                    tint = PocketLibTheme.colors.primary
                )
            }
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun UserInfo() {
    val avatar = LocalUser.current?.avatar
    Column(
        modifier = Modifier
            .padding(all = 8.dp)
            .padding(top = 0.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GlideImage(
                contentScale = ContentScale.Crop,
                model = avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
                    .background(PocketLibTheme.colors.surfaceVariant),
            ) {
                it.error(R.drawable.person)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.person)
                    .load(avatar)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = LocalUser.current?.username.toString(),
                style = PocketLibTheme.textStyles.largeStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
            Text(
                text = LocalUser.current?.email.toString(),
                style = PocketLibTheme.textStyles.normalStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
        }
    }
}

@Composable
private fun ProfileTabRow(
    viewModel: UserViewModel,
) {
    val tabRowIndex by viewModel.tabRowItem.observeAsState(0)

    val books by viewModel.postsList.observeAsState(emptyList())
    val state by viewModel.state.observeAsState(UserState.Initial)
    val myBooksDisplayMode by viewModel.myBooksDisplayMode.observeAsState(DisplayMode.LIST)
    val favoritesDisplayMode by viewModel.favoritesDisplayMode.observeAsState(DisplayMode.LIST)

    val navigation = LocalNavigationState.current

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
                UserState.Initial -> {  }
                UserState.Loading -> { LoadingCircle() }
                UserState.Content -> {
                    BookColumn(
                        displayMode = myBooksDisplayMode,
                        books = books,
                        navigateToPost = { id -> navigation.navigateToPostFromProfile(id) }
                    )
                }
            }
        }
        1 -> {
            BookColumn(
                displayMode = favoritesDisplayMode,
                navigateToPost = {  }
            )
        }
    }
}