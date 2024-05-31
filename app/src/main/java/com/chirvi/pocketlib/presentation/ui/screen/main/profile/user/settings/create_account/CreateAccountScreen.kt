package com.chirvi.pocketlib.presentation.ui.screen.main.profile.user.settings.create_account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.BackButton
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldPassword
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldWithLabel
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun CreateAccountScreen(
    updateUser: () -> Unit,
) {
    val viewModel = hiltViewModel<CreateAccountViewModel>()
    val state by viewModel.state.observeAsState(CreateAccountState.Initial)
    val navigation = LocalNavigationState.current

    Column(
        modifier = Modifier
            .background(color = PocketLibTheme.colors.background)
            .fillMaxSize()
    ) {
        CreateAccountAppTopBar(navigateToBack = { viewModel.navigateToBack(navigation) })
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            when(state) {
                CreateAccountState.Initial -> { Initial(viewModel = viewModel) }
                CreateAccountState.Complete -> { Complete(viewModel = viewModel, updateUser = updateUser) }
                CreateAccountState.Loading -> { LoadingCircle() }
            }
        }
    }
}

@Composable
private fun Complete(
    viewModel: CreateAccountViewModel,
    updateUser: () -> Unit,
) {
    val navigationState = LocalNavigationState.current
    updateUser()
    viewModel.navigateToProfileScreen(navigationState = navigationState)
}

@Composable
private fun Initial(
    viewModel: CreateAccountViewModel
) {
    val error by viewModel.errorMessage.observeAsState("")

    Column {
        AddAvatar(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        TextFields(viewModel = viewModel)
        Text(
            modifier = Modifier
                .padding(
                    vertical = 4.dp,
                    horizontal = 8.dp
                ),
            text = error,
            style = PocketLibTheme.textStyles.normalStyle.copy(
                color = PocketLibTheme.colors.error
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        ButtonWithText(
            text = stringResource(id = R.string.create_a_new_account),
            colors = ButtonDefaults.buttonColors(
                containerColor = PocketLibTheme.colors.tertiary,
                contentColor = PocketLibTheme.colors.onTertiary
            ),
            onClickListener = { viewModel.registration() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateAccountAppTopBar(
    navigateToBack: () -> Unit,
) {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.create_a_new_account),
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
        },
        navigationIcon = {
            BackButton(
                onClickListener = navigateToBack
            )
        }
    )
}

@Composable
private fun TextFields(
    viewModel: CreateAccountViewModel
) {
    val textName by viewModel.textName.observeAsState("")
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
        }
    }
}

@Composable
private fun AddAvatar(
    viewModel: CreateAccountViewModel
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