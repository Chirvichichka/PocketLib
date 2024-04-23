package com.chirvi.pocketlib.presentation.ui.screen.introduction.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.chirvi.pocketlib.presentation.ui.common.SeparativeLine
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldPassword
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldWithLabel
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.login.LoginViewModel
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationMainState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun IntroductionLoginScreen(
    updateUser: () -> Unit
) {
    val viewModel = hiltViewModel<IntroductionLoginViewModel>()
    val state by viewModel.state.observeAsState(IntroductionLoginState.Initial)
    val navigationMainState = LocalNavigationMainState.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when(state) {
            IntroductionLoginState.Complete -> {
                updateUser()
                viewModel.onComplete(navigationMainState)
            }
            IntroductionLoginState.Initial -> { Initial(viewModel = viewModel) }
            IntroductionLoginState.Loading -> { LoadingCircle() }
        }
    }
}

@Composable
private fun Initial(
    viewModel: IntroductionLoginViewModel
) {
    Text(
        text = "Добро пожаловать!",
        style = PocketLibTheme.textStyles.largeStyle.copy(
            color = PocketLibTheme.colors.onBackground
        )
    )
    SeparativeLine()
    TextFields(viewModel = viewModel)
    Spacer(modifier = Modifier.height(16.dp))
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
    SeparativeLine()
    TextButton(
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = "Регистрация",
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.primary
            )
        )
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