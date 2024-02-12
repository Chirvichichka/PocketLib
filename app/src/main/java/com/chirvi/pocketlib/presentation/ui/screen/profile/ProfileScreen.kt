package com.chirvi.pocketlib.presentation.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.common.BookColumn
import com.chirvi.pocketlib.presentation.common.ButtonWithText
import com.chirvi.pocketlib.presentation.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.models.Book
import com.chirvi.pocketlib.presentation.navigation.item.ProfileTabRowItem
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun ProfileScreen() {
    val viewModel = hiltViewModel<ProfileViewModel>()

    Scaffold(
        containerColor = PocketLibTheme.colors.primary,

        topBar = { ProfileTopAppBar() },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            UserInfo()
            ProfileTabRow(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTopAppBar() {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile),
                style = PocketLibTheme.textStyles.primaryLarge.copy(
                    color = PocketLibTheme.colors.primary,
                    fontStyle = FontStyle.Italic
                )
            )
        },
        actions = {
            IconButton(
                onClick = {
                    /*TODO*/
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = null,
                    tint = PocketLibTheme.colors.black
                )
            }
        }
    )
}

@Composable
private fun UserInfo() {
    Spacer(modifier = Modifier.height(0.dp))
    Column(
        modifier = Modifier
            .padding(all = 16.dp,)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = R.drawable.test_image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "User name",
                    style = PocketLibTheme.textStyles.primaryLarge
                )
                Text(
                    text = "User id",
                    style = PocketLibTheme.textStyles.primary,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ButtonWithText(
            text = "Редактировать",
            onClickListener = { /*TODO*/ }
        )
    }
}

@Composable
private fun ProfileTabRow(
    viewModel: ProfileViewModel
) {
    val tabRowIndex by viewModel.tabRowItem.observeAsState(0)

    val items = listOf(
        ProfileTabRowItem.MyBooks,
        ProfileTabRowItem.Favorite
    )

    TabRow(
        containerColor = PocketLibTheme.colors.primary,
        selectedTabIndex = tabRowIndex,
        contentColor = PocketLibTheme.colors.black,
        indicator = { tabPosition ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPosition[tabRowIndex]),
                color = PocketLibTheme.colors.tertiary
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
                        tint = PocketLibTheme.colors.tertiary,
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
            BookColumn(
                book = Book(),
            )
        }
        1 -> {
            BookColumn(
                grid = false,
                count = 5,
                book = Book()
            )
        }
    }
}