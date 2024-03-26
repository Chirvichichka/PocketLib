package com.chirvi.pocketlib.presentation.ui.screen.main.book_add

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.common.AddPictureFromGallery
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldWithLabel
import com.chirvi.pocketlib.presentation.ui.screen.main.home.feed.FeedViewModel
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme


@Composable
fun AddBookScreen(
    toHomeScreen: () -> Unit,
) {
    val viewModel = hiltViewModel<AddBookViewModel>()
    val state by viewModel.state.observeAsState(AddBookState.Initial)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.primary)
    ) {
        AddBookTopAppBar()
        when(state) {
            AddBookState.Initial -> { Initial(viewModel = viewModel) }
            AddBookState.Loading -> { Loading() }
            AddBookState.Saved -> {
                Saved(
                    toHomeScreen = toHomeScreen,
                    stateChange = { viewModel.stateChange() }
                )
            }
        }
    }
}

@Composable
private fun Initial(
    viewModel: AddBookViewModel
) {
    val image by viewModel.image.observeAsState(null)
    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize()
    ) {
        LoadButton()
        Spacer(modifier = Modifier.height(16.dp))
        AddPicture(
            image = image,
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

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = PocketLibTheme.colors.secondary
        )
    }
}

@Composable
private fun Saved(
    stateChange: () -> Unit,
    toHomeScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Книга успешно сохранена",
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.dark
            )
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.5f))
        ButtonWithText(
            text = "Перейти на главный экран",
            onClickListener = {
                toHomeScreen()
                stateChange()
            }
        )
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
    image: Uri?,
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
            image = image
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

    Spacer(modifier = Modifier.height(16.dp))
    TextFieldWithLabel(
        text = textName,
        textLabel = stringResource(id = R.string.enter_name),
        onValueChange = { newText -> viewModel.onValueChangeName(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextFieldWithLabel(
        text = textAuthor,
        textLabel = stringResource(id = R.string.enter_author),
        onValueChange = { newText -> viewModel.onValueChangeAuthor(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextFieldWithLabel(
        modifier = Modifier.height(120.dp),
        text = textDescription,
        singleLine = false,
        textLabel = stringResource(id = R.string.enter_description),
        onValueChange = { newText -> viewModel.onValueChangeDescription(newText) }
    )
}

@Composable
private fun Genres() {

}