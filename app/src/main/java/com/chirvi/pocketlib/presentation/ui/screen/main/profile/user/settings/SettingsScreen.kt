package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.ui.common.CustomSwitch
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.SeparativeLine
import com.chirvi.pocketlib.presentation.ui.common.button.BackButton
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.EditTextField
import com.chirvi.pocketlib.presentation.ui.theme.ColorScheme
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import com.chirvi.pocketlib.presentation.ui.theme.blue_theme.seedBlue
import com.chirvi.pocketlib.presentation.ui.theme.green_theme.seedGreen
import com.chirvi.pocketlib.presentation.ui.theme.pink_theme.seedPink
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(
    changeTheme: () -> Unit,
    changeColorScheme: (ColorScheme) -> Unit,
    updateUser: () -> Unit,
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val navigation = LocalNavigationState.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
    ) {
        SettingsTopAppBar{ viewModel.navigateToBack(navigation) }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(all = 8.dp)
        ) {
            Account(
                onCreateAccountClick = { viewModel.navigateToRegistration(navigation) },
                onLoginClick = { viewModel.navigateToLogin(navigation) },
                updateUser = updateUser
            )
            SeparativeLine()
            SettingsTheme(viewModel = viewModel, themeChange = changeTheme, colorSchemeChange = changeColorScheme)
//            SeparativeLine()
//            UserEdit()
            SeparativeLine()
            SettingsDisplay(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopAppBar(
    navigateToBack: () -> Unit,
) {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.settings),
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.onBackground,
                )
            )
        },
        actions = {
            IconButton(
                onClick = {
                    //todo
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = null,
                    tint = PocketLibTheme.colors.primary
                )
            }
        },
        navigationIcon = {
            BackButton(
                onClickListener = navigateToBack
            )
        }
    )
}

@Composable
private fun Account(
    onCreateAccountClick: () -> Unit,
    onLoginClick: () -> Unit,
    updateUser: () -> Unit
) {
    val navigationState = LocalNavigationState.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Title(text = stringResource(id = R.string.account_settings))
        Column(
            modifier = Modifier.padding(start = 4.dp, top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            ButtonWithText(
                text = stringResource(id = R.string.create_a_new_account),
                onClickListener = { onCreateAccountClick() }
            )
            ButtonWithText(
                text = "Сменить аккаунт",//todo
                onClickListener = { onLoginClick() }
            )
            ButtonWithText(
                text = "Выйти", //todo
                onClickListener = {
                    FirebaseAuth.getInstance().signOut()
                    updateUser()
                    navigationState.navigateTo(Screen.Introduction.route) //todo
                }
            )
        }
    }
}

@Composable
private fun UserEdit() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Title(text = stringResource(id = R.string.edit_user))
        Column(
            modifier = Modifier
                .padding(start = 4.dp,  top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.test_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(id = R.string.edit_avatar),
                    style = PocketLibTheme.textStyles.normalStyle.copy(
                        color = PocketLibTheme.colors.onBackground
                    )
                )
            }
            EditTextField(
                label = "Изменить никнейм",
                text = "", //todo
                onValueChange = {  }
            )
        }
    }
}

@Composable
fun SettingsDisplay(
    viewModel: SettingsViewModel
) {
    val feedSwitchState by viewModel.feedSwitchState.observeAsState(false)
    val myBooksSwitchState by viewModel.myBooksSwitchState.observeAsState(false)
    val favoriteSwitchState by viewModel.favoriteSwitchState.observeAsState(false)

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(id = R.string.card_display),
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.onBackground,
                fontWeight = FontWeight.Bold
            )
        )
        RowSwitch(
            textId = R.string.feed_switch,
            state = feedSwitchState,
            onClickListener = { viewModel.feedChange() }
        )
        RowSwitch(
            textId = R.string.my_books_switch,
            state = myBooksSwitchState,
            onClickListener = { viewModel.myBooksChange() }
        )
        RowSwitch(
            textId = R.string.favorite_switch,
            state =favoriteSwitchState,
            onClickListener = { viewModel.favoritesChange() }
        )
    }
}

@Composable
private fun RowSwitch(
    textId: Int,
    state: Boolean,
    onClickListener: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.7f),
            text = stringResource(id = textId),
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.onBackground
            )
        )
        CustomSwitch(
            state = state,
            onClickListener = onClickListener
        )
    }
}

@Composable
private fun SettingsTheme(
    viewModel: SettingsViewModel,
    themeChange: () -> Unit,
    colorSchemeChange: (ColorScheme) -> Unit
) {
    val isDarkTheme by viewModel.isDarkTheme.observeAsState(false)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Title(text = "Настройки темы")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.7f),
                text = "Dark theme",
                style = PocketLibTheme.textStyles.normalStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
            CustomSwitch(
                state = isDarkTheme,
                onClickListener = {
                    viewModel.themeModChange()
                    themeChange()
                }
            )
        }
        Column {
            Text(
                text = "Цветовая схема: ", //todo
                style = PocketLibTheme.textStyles.normalStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                ThemeCircle(tint = seedPink, colorSchemeChange = { colorSchemeChange(ColorScheme.PINK) })
                ThemeCircle(tint = seedGreen, colorSchemeChange = { colorSchemeChange(ColorScheme.GREEN) })
                ThemeCircle(tint = seedBlue, colorSchemeChange = { colorSchemeChange(ColorScheme.BLUE) })
            }
        }
    }
}

@Composable
private fun ThemeCircle(
    tint: Color,
    colorSchemeChange: () -> Unit,
) {
    IconButton(
        onClick = {
            colorSchemeChange()
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
private fun Title(
    text: String
) {
    Text(
        text = text,
        style = PocketLibTheme.textStyles.largeStyle.copy(
            color = PocketLibTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )
    )
}