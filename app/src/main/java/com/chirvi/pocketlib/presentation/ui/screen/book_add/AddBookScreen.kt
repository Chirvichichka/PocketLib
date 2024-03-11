package com.chirvi.pocketlib.presentation.ui.screen.book_add

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.common.AddPictureFromGallery
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.PocketLibTextField
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme


@Composable
fun AddBookScreen() {
    val viewModel = hiltViewModel<AddBookViewModel>()

    val imageUri by viewModel.image.observeAsState(null)
   // var imageUri by remember { mutableStateOf<Uri?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.primary)
    ) {
        AddBookTopAppBar()
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxSize()
        ) {
            LoadButton()
            Spacer(modifier = Modifier.height(16.dp))
            AddPicture(
                imageUri = imageUri,
                loadImage = { viewModel.changeImage(it) }
            )
            TextFields(viewModel = viewModel)
            Spacer(modifier = Modifier.weight(1f))
            Genres()
            ButtonWithText(
                alternativeColorScheme = false,
                text = stringResource(id = R.string.save),
                onClickListener = { viewModel.saveBook() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddBookTopAppBar() {
    PocketLibTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.add_book),
                style = PocketLibTheme.textStyles.topAppBarStyle.copy(
                    color = PocketLibTheme.colors.primary,
                )
            )
        }
    )
}

@Composable
private fun LoadButton() {
    ButtonWithText(
        text = "Загрузить",
        onClickListener = {
            //todo
        }
    )
}

@Composable
private fun AddPicture(
    imageUri: Uri?,
    loadImage: (Uri) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                uri?.let {
                    loadImage(it)
                }
            }
        )
        AddPictureFromGallery(
            load = { galleryLauncher.launch("image/*") },
            imageUri = imageUri
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.add_image),
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.dark
            )
        )
    }
}

@Composable
private fun TextFields(
    viewModel: AddBookViewModel
) {
    val textName by viewModel.textName.observeAsState("")
    val textAuthor by viewModel.textAuthor.observeAsState("")
    val textDescription by viewModel.textDescription.observeAsState("")

    val focusRequesterAuthor = remember { FocusRequester() }
    val focusRequesterDescription = remember { FocusRequester() }

    Spacer(modifier = Modifier.height(16.dp))
    PocketLibTextField(
        onKeyboardActions = { focusRequesterAuthor.requestFocus() },
        text = textName,
        placeHolderText = stringResource(id = R.string.enter_name),
        onValueChange = { newText -> viewModel.onValueChangeName(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    PocketLibTextField(
        onKeyboardActions = { focusRequesterDescription.requestFocus() },
        modifier = Modifier.focusRequester(focusRequesterAuthor),
        text = textAuthor,
        placeHolderText = stringResource(id = R.string.enter_author),
        onValueChange = { newText -> viewModel.onValueChangeAuthor(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    PocketLibTextField(
        modifier = Modifier
            .height(120.dp)
            .focusRequester(focusRequesterDescription),
        text = textDescription,
        singleLine = false,
        placeHolderText = stringResource(id = R.string.enter_description),
        onValueChange = { newText -> viewModel.onValueChangeDescription(newText) }
    )
}

@Composable
private fun Genres() {

}