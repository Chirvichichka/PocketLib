package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.SeparativeLine
import com.chirvi.pocketlib.presentation.ui.common.button.BackButton
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldPassword
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldWithLabel
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(
    onBackPressed: () -> Unit,
    toProfileScreen: (FirebaseUser?) -> Unit,
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val state by viewModel.state.observeAsState(LoginState.Initial)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
    ) {
        LoginAppTopBar(onBackPressed = onBackPressed)
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            when(state) {
                LoginState.Initial -> { Initial(viewModel = viewModel) }
                LoginState.Complete -> { Complete(toHomeScreen = toProfileScreen) }
                LoginState.Loading -> { LoadingCircle() }
            }
        }
    }
}

@Composable
private fun Complete(
    toHomeScreen: (FirebaseUser?) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Вход выполнен успешно",  //todo
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.onBackground
            )
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.5f))
        ButtonWithText(
            text = "Перейти на главный экран",  //todo
            onClickListener = { toHomeScreen(Firebase.auth.currentUser) }
        )
    }
}

@Composable
private fun Initial(
    viewModel: LoginViewModel
) {
    Column {
        TextFields(viewModel = viewModel)
        SeparativeLine()
        ButtonWithText(
            text = "Войти",
            colors = ButtonDefaults.buttonColors(
                containerColor = PocketLibTheme.colors.tertiary,
                contentColor = PocketLibTheme.colors.onTertiary
            ),
            onClickListener = {
                viewModel.authentication()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginAppTopBar(
    onBackPressed: () -> Unit,
) {
    PocketLibTopAppBar(
        title = {
            Text(
                text = "Войти в аккаунт", //toto
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.onSecondaryContainer
                )
            )
        },
        navigationIcon = {
            BackButton(
                onClickListener = onBackPressed
            )
        }
    )
}

@Composable
private fun TextFields(
    viewModel: LoginViewModel
) {
    val textEMail by viewModel.textEMail.observeAsState("")
    val textPassword by viewModel.textPassword.observeAsState("")
    val error by viewModel.errorMessage.observeAsState("")

    Card(
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            TextFieldWithLabel(
                text = textEMail,
                textLabel = stringResource(id = R.string.enter_e_mail),
                keyboardType = KeyboardType.Email,
                onValueChange = { newText -> viewModel.onValueChangeEMail(newText) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldPassword(
                text = textPassword,
                textLabel = "Укажите ваш пароль",//todo
                onValueChange = { newText -> viewModel.onValueChangePassword(newText) }
            )
            if (error.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    text = error,
                    style = PocketLibTheme.textStyles.normalStyle.copy(
                        color = PocketLibTheme.colors.error
                    )
                )
            }
        }
    }
}