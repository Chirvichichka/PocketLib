package com.chirvi.pocketlib.presentation.ui.screen.main.introduction.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.common.SeparativeLine
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldPassword
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldWithLabel
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun IntroductionLoginScreen(
    updateUser: () -> Unit
) {
    val viewModel = hiltViewModel<IntroductionLoginViewModel>()
    val state by viewModel.state.observeAsState(IntroductionLoginState.Initial)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
            .padding(all = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when(state) {
            IntroductionLoginState.Complete -> { updateUser() }
            IntroductionLoginState.Initial -> { Initial(viewModel = viewModel) }
            IntroductionLoginState.Loading -> { LoadingCircle() }
        }
    }
}

@Composable
private fun Initial(
    viewModel: IntroductionLoginViewModel
) {
    val navigationState = LocalNavigationState.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Title()
        Spacer(modifier = Modifier.height(32.dp))
        TextFields(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        ButtonWithText(
            text = "Войти",
            colors = ButtonDefaults.buttonColors(
                containerColor = PocketLibTheme.colors.primary,
                contentColor = PocketLibTheme.colors.onPrimary
            ),
            onClickListener = {
                viewModel.authentication()
            }
        )
        SeparativeLine()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    viewModel.navigateToRegistration(navigationState)
                }
            ) {
                Text(
                    text = "Регистрация",
                    style = PocketLibTheme.textStyles.normalStyle.copy(
                        color = PocketLibTheme.colors.primary
                    )
                )
            }
        }
    }
}

@Composable
private fun Title() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.book),
            contentDescription = null,
            tint = PocketLibTheme.colors.primary
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Добро пожаловать!",
                style = PocketLibTheme.textStyles.largeStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                ),
            )
            Text(
                text = "Для продолжения введите почту и пароль",
                style = PocketLibTheme.textStyles.smallStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
        }
    }
}

@Composable
private fun TextFields(
    viewModel: IntroductionLoginViewModel
) {
    val textEmail by viewModel.textEmail.observeAsState("")
    val textPassword by viewModel.textPassword.observeAsState("")

    Card(
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            TextFieldWithLabel(
                text = textEmail,
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
        }
    }
}