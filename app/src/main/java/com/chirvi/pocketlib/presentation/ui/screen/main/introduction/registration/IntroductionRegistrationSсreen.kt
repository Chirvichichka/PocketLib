package com.chirvi.pocketlib.presentation.ui.screen.main.introduction.registration

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.chirvi.pocketlib.presentation.ui.common.AddPictureFromGallery
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.common.SeparativeLine
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldPassword
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldWithLabel
import com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.create_account.CreateAccountViewModel
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun IntroductionRegistrationScreen(
    updateUser: () -> Unit
) {
    val viewModel = hiltViewModel<IntroductionRegistrationViewModel>()
    val state by viewModel.state.observeAsState(IntroductionRegistrationState.Initial)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
            .padding(all = 8.dp)
    ) {
        when(state) {
            IntroductionRegistrationState.Complete -> { updateUser() }
            IntroductionRegistrationState.Initial -> { Content(viewModel = viewModel) }
            IntroductionRegistrationState.Loading -> { LoadingCircle() }
        }
    }
}

@Composable
private fun Content(
    viewModel: IntroductionRegistrationViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Title()
        Spacer(modifier = Modifier.height(32.dp))
        AddAvatar(viewModel = viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        RegistrationFields(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        RegistrationButtons(viewModel = viewModel)
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
                text = "Регистрация",
                style = PocketLibTheme.textStyles.largeStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                ),
            )
            Text(
                text = "Для продолжения заполните все поля",
                style = PocketLibTheme.textStyles.smallStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
        }
    }
}

@Composable
private fun AddAvatar(
    viewModel: IntroductionRegistrationViewModel
) {
    val image by viewModel.image.observeAsState(null)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.surfaceVariant
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            AddPictureFromGallery(
                changeImage = { viewModel.changeImage(it) } ,
                image = image
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = stringResource(id = R.string.add_image),
                style = PocketLibTheme.textStyles.largeStyle.copy(
                    color = PocketLibTheme.colors.onSurface
                )
            )
            IconButton(onClick = { viewModel.deleteImage() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    tint = PocketLibTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
private fun RegistrationFields(
    viewModel: IntroductionRegistrationViewModel

) {
    val textName by viewModel.textName.observeAsState("")
    val textEmail by viewModel.textEmail.observeAsState("")
    val textPassword by viewModel.textPassword.observeAsState("")
    val textConfirmPassword by viewModel.textConfirmPassword.observeAsState("")

    Card(
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            TextFieldWithLabel(
                text = textName,
                textLabel = stringResource(id = R.string.account_name),
                onValueChange = { newText -> viewModel.onValueChangeName(newText) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldWithLabel(
                text = textEmail,
                textLabel = stringResource(id = R.string.enter_e_mail),
                keyboardType = KeyboardType.Email,
                onValueChange = { newText -> viewModel.onValueChangeEMail(newText) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldPassword(
                text = textPassword,
                textLabel = stringResource(id = R.string.enter_password),
                onValueChange = { newText -> viewModel.onValueChangePassword(newText) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldPassword(
                text = textConfirmPassword,
                textLabel = stringResource(id = R.string.enter_confirm_password),
                onValueChange = { newText -> viewModel.onValueChangeConfirmPassword(newText) },
            )
        }
    }
}

@Composable
private fun RegistrationButtons(
    viewModel: IntroductionRegistrationViewModel
) {
    val navigationState = LocalNavigationState.current

    ButtonWithText(
        text = "Создать аккаунт", //todo
        colors = ButtonDefaults.buttonColors(
            containerColor = PocketLibTheme.colors.primary,
            contentColor = PocketLibTheme.colors.onPrimary
        ),
        onClickListener = {

        }
    )
    SeparativeLine()
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = {

            }
        ) {
            Text(
                text = "Войти",
                style = PocketLibTheme.textStyles.normalStyle.copy(
                    color = PocketLibTheme.colors.primary
                )
            )
        }
    }
}