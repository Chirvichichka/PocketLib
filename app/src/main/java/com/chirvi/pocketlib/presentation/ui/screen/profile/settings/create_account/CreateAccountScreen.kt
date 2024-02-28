package com.chirvi.pocketlib.presentation.ui.screen.profile.settings.create_account

import android.media.Image
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.BackButton
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldWithLabel
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun CreateAccountScreen(
    onBackPressed: () -> Unit,
    ) {
    val viewModel = hiltViewModel<CreateAccountViewModel>()
    val confirm by viewModel.isPasswordConfirm.observeAsState(false)

    Column(
        modifier = Modifier
            .background(color = PocketLibTheme.colors.primary)
            .fillMaxSize()
    ) {
        CreateAccountAppTopBar(onBackPressed = onBackPressed)
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            AddAvatar()
            Spacer(modifier = Modifier.height(16.dp))
            TextFields(viewModel = viewModel)
            Spacer(modifier = Modifier.weight(1f))
            ButtonWithText(
                text = if (confirm) {
                    "true"
                } else {
                    "false"
                }, //todo
                onClickListener = {
                    viewModel.confirmPassword()
                } //todo
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateAccountAppTopBar(
    onBackPressed: () -> Unit,
) {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.create_a_new_account),
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.primary
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
    viewModel: CreateAccountViewModel
) {

    val textName by viewModel.textName.observeAsState("")
    val textEMail by viewModel.textEMail.observeAsState("")
    val textPassword by viewModel.textPassword.observeAsState("")
    val textConfirmPassword by viewModel.textConfirmPassword.observeAsState("")

    TextFieldWithLabel(
        text = textName,
        textLabel = stringResource(id = R.string.account_name),
        onValueChange = { newText -> viewModel.onValueChangeName(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextFieldWithLabel(
        text = textEMail,
        textLabel = stringResource(id = R.string.enter_e_mail),
        keyboardType = KeyboardType.Email,
        onValueChange = { newText -> viewModel.onValueChangeEMail(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextFieldWithLabel(
        text = textPassword,
        textLabel = stringResource(id = R.string.enter_password),
        keyboardType = KeyboardType.Password,
        passwordTextField = true,
        onValueChange = { newText -> viewModel.onValueChangePassword(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextFieldWithLabel(
        text = textConfirmPassword,
        textLabel = stringResource(id = R.string.enter_confirm_password),
        keyboardType = KeyboardType.Password,
        passwordTextField = true,
        onValueChange = { newText -> viewModel.onValueChangeConfirmPassword(newText) }
    )
}

@Composable
private fun AddAvatar() {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
            }
        }
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(110.dp)
                .background(
                    color = PocketLibTheme.colors.secondary,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    galleryLauncher.launch("image/*")
                }
        ) {
            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUri),
                    contentDescription = null,
                    modifier = Modifier.size(110.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    modifier = Modifier.size(60.dp),
                    tint = PocketLibTheme.colors.tertiary,
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopStart),
                onClick = {
                    imageUri = null
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.add_image),
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.dark
            )
        )
    }
}