package com.chirvi.pocketlib.presentation.ui.screen.main.book_add

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.common.AddPictureFromGallery
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.common.SeparativeLine
import com.chirvi.pocketlib.presentation.ui.common.button.ButtonWithText
import com.chirvi.pocketlib.presentation.ui.common.text_field.TextFieldWithLabel
import com.chirvi.pocketlib.presentation.ui.theme.LocalNavigationState
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme


@Composable
fun AddBookScreen(
) {
    val viewModel = hiltViewModel<AddBookViewModel>()
    val state by viewModel.state.observeAsState(AddBookState.Initial)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PocketLibTheme.colors.background)
    ) {
        AddBookTopAppBar()
        when(state) {
            AddBookState.Initial -> { Initial(viewModel = viewModel) }
            AddBookState.Loading -> { LoadingCircle() }
            AddBookState.Saved -> {
                Saved(viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun Initial(
    viewModel: AddBookViewModel
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AddPicture(viewModel = viewModel)
        SeparativeLine()
        TextFields(viewModel = viewModel)
        SeparativeLine()
        Genres(viewModel = viewModel)
        SeparativeLine()
        LoadButton()
        SeparativeLine()
        ButtonWithText(
            text = stringResource(id = R.string.save),
            onClickListener = { viewModel.saveBook() },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = PocketLibTheme.colors.tertiary,
                contentColor = PocketLibTheme.colors.onTertiary
            )
        )
    }
}

@Composable
private fun Saved(
    viewModel: AddBookViewModel
) {
    val navigationState = LocalNavigationState.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Книга успешно сохранена",  //todo
            style = PocketLibTheme.textStyles.largeStyle.copy(
                color = PocketLibTheme.colors.onBackground
            )
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.5f))
        ButtonWithText(
            text = "Перейти на главный экран",  //todo
            onClickListener = {
                viewModel.toFeed(navigationState)
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
                    color = PocketLibTheme.colors.onSecondaryContainer,
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
    viewModel: AddBookViewModel
) {
    val image by viewModel.image.observeAsState(null)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.secondaryContainer
        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AddPictureFromGallery(
                image = image,
                changeImage = { viewModel.changeImage(it) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = stringResource(id = R.string.add_image),
                style = PocketLibTheme.textStyles.largeStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
            IconButton(onClick = {viewModel.deleteImage() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null
                )
            }
        }
    }

}

@Composable
private fun TextFields(
    viewModel: AddBookViewModel
) {
    val textName by viewModel.textName.observeAsState("")
    val textAuthor by viewModel.textAuthor.observeAsState("")
    val textDescription by viewModel.textDescription.observeAsState("")
    Card(
        colors = CardDefaults.cardColors(
            containerColor = PocketLibTheme.colors.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
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

    }

}

@Composable
private fun Genres(
    viewModel: AddBookViewModel
) {
    val opened by viewModel.opened.observeAsState(false)
    val genresList by viewModel.confirmedGenres.observeAsState(emptyList())

    val bullet = "\t\u2022"
    val genres = genresList.joinToString("\n") { "$bullet $it" } //todo во вьюмодель

    Column {
        ButtonWithText(
            text = "Указажите жанры",  //todo
            onClickListener = { viewModel.openedChange() }
        )
        if (genres.isNotEmpty()) {
            Text(
                text = genres,
                style = PocketLibTheme.textStyles.largeStyle.copy(
                    color = PocketLibTheme.colors.onBackground
                )
            )
        }
    }
    if(opened) {
        GenresDialog(viewModel = viewModel)
    }
}

@Composable
private fun GenresDialog(
    viewModel: AddBookViewModel
) {
    val testStyle = PocketLibTheme.textStyles.smallStyle.copy(
        color = PocketLibTheme.colors.onBackground
    )
    Dialog(
        onDismissRequest = { viewModel.openedChange() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.7f)
                .padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = PocketLibTheme.colors.background
            )
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                ColumnGenres(viewModel = viewModel)
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            viewModel.cancelGenres()
                            viewModel.openedChange()
                        }
                    ) {
                        Text(
                            text = "Сбросить", //todo
                            style = testStyle
                        )
                    }
                    TextButton(
                        onClick = {
                            viewModel.confirmGenres()
                            viewModel.openedChange()
                        }
                    ) {
                        Text(
                            text = "Подтвердить",  //todo
                            style = testStyle
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnGenres(
    viewModel: AddBookViewModel
) {
    val genresWithSelected by viewModel.genresWithSelected.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .verticalScroll(rememberScrollState())
    ) {
        genresWithSelected?.forEach { (genre, selected) ->
            InputChip(
                colors = InputChipDefaults.inputChipColors(
                    containerColor = PocketLibTheme.colors.background,
                    labelColor = PocketLibTheme.colors.onBackground,

                    selectedLabelColor = PocketLibTheme.colors.tertiaryContainer,
                    selectedContainerColor = PocketLibTheme.colors.onTertiaryContainer,
                ),
                selected = selected,
                onClick = { viewModel.toggleSelected(genre) },
                label = {
                    Text(
                        text = genre,
                        style = PocketLibTheme.textStyles.normalStyle
                    )
                }
            )
        }
    }
}
