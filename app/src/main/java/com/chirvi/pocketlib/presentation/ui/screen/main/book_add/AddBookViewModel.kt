package com.chirvi.pocketlib.presentation.ui.screen.main.book_add

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.posts.CreateIdUseCase
import com.chirvi.domain.usecase.posts.SaveBookUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val saveBookUseCase: SaveBookUseCase,
    private val createIdUseCase: CreateIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData<AddBookState>(AddBookState.Initial)
    val state: LiveData<AddBookState> = _state

    private val _image = MutableLiveData<Uri>(null)
    val image: LiveData<Uri> = _image

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textAuthor = MutableLiveData("")
    val textAuthor: LiveData<String> = _textAuthor

    private val _textDescription = MutableLiveData("")
    val textDescription: LiveData<String> = _textDescription

    private val postId = createIdUseCase()

    fun changeImage(imageUri: Uri) { _image.value = imageUri }
    fun onValueChangeDescription(text: String) { _textDescription.value = text }
    fun onValueChangeName(text: String) { _textName.value = text }
    fun onValueChangeAuthor(text: String) { _textAuthor.value = text }
    fun saveBook() { viewModelScope.launch { suspendSaveBook() } }
    private suspend fun suspendSaveBook() {
        _state.value = AddBookState.Loading
        val book = BookPresentation(
            id = postId,
            name = textName.value?:"",
            author = textAuthor.value?:"",
            description = textDescription.value?:"",
            image = image.value.toString(),
        ).toDomain()
        viewModelScope.launch{ saveBookUseCase(book = book) }.join()
        _state.value = AddBookState.Saved
    }
}