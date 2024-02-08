package com.chirvi.pocketlib.presentation.ui.screen.profile

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.common.ButtonWithText
import com.chirvi.pocketlib.presentation.navigation.ProfileTabRowItem
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun ProfileScreen(
) {
    Scaffold(
        topBar = { TopBar() },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            UserInfo()
            ProfileTabRow()
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .background(
                color = PocketLibTheme.colors.tertiary
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Тут что то будет потом",
            style = PocketLibTheme.textStyles.primaryLarge.copy(
                color = PocketLibTheme.colors.primary
            )
        )
        Spacer(modifier = Modifier.weight(0.6f))
        IconButton(
            onClick = {
                /*TODO*/
            }
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = R.drawable.settings),
                contentDescription = null,
                tint = PocketLibTheme.colors.black
            )
        }
    }
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
                    .clip(
                        shape = CircleShape
                    ),
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
                    style = PocketLibTheme.textStyles.primarySmall,
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
private fun ProfileTabRow() {
    val items = listOf(
        ProfileTabRowItem.MyBooks,
        ProfileTabRowItem.Favorite
    )
    TabRow(
        selectedTabIndex = 0,
        contentColor = PocketLibTheme.colors.black,
    ) {
        items.forEach { item ->
            Tab(
                text = {
                    Text(
                        text = item.title
                    )
                },
                selected = true,
                onClick = { /*TODO*/ })
        }
    }
}