package com.chirvi.pocketlib.presentation.ui.screen.book_add

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.common.ButtonWithText
import com.chirvi.pocketlib.presentation.common.PocketLibTextField
import com.chirvi.pocketlib.presentation.common.PocketLibTopAppBar
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun AddBookScreen() {
    val viewModel = hiltViewModel<AddBookViewModel>()


    Scaffold(
        containerColor = PocketLibTheme.colors.primary,
        topBar = { AddBookTopAppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(all = 16.dp)
                .fillMaxSize()
        ) {
            AddPicture()
            TextFields(viewModel = viewModel)
            Spacer(modifier = Modifier.weight(1f))
            ButtonWithText(text = "Сохранить", onClickListener = {})
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
                style = PocketLibTheme.textStyles.primaryLarge.copy(
                    color = PocketLibTheme.colors.primary,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    )
}

@Composable
private fun AddPicture() {
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
                .clickable{
                    //todo
                }
        ) {
            Icon(
                modifier = Modifier.size(60.dp),
                tint = PocketLibTheme.colors.tertiary,
                painter = painterResource(id = R.drawable.add),
                contentDescription = null)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.add_image),
            style = PocketLibTheme.textStyles.primaryLarge.copy(
                color = PocketLibTheme.colors.black
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

    Spacer(modifier = Modifier.height(16.dp))
    PocketLibTextField(
        text = textName,
        placeHolderText = stringResource(id = R.string.enter_name),
        leadingIconId = R.drawable.add,
        onValueChange = { newText -> viewModel.onValueChangeName(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    PocketLibTextField(
        text = textAuthor,
        placeHolderText = stringResource(id = R.string.enter_author),
        leadingIconId = R.drawable.add,
        onValueChange = { newText -> viewModel.onValueChangeAuthor(newText) }
    )
    Spacer(modifier = Modifier.height(16.dp))
}