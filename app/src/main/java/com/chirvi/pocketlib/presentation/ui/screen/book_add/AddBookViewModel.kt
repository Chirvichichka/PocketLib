package com.chirvi.pocketlib.presentation.ui.screen.book_add

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.storage.StorageRepository
import com.chirvi.domain.usecase.posts.AddPostUseCase
import com.chirvi.domain.usecase.posts.GetUrlUseCase
import com.chirvi.domain.usecase.posts.SaveImageUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val addPostUseCase: AddPostUseCase,
    private val getUrlUseCase: GetUrlUseCase,
    private val saveImageUseCase: SaveImageUseCase,
) : ViewModel() {

    private val _image = MutableLiveData<Uri>()
    val image: LiveData<Uri> = _image

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textAuthor = MutableLiveData("")
    val textAuthor: LiveData<String> = _textAuthor

    private val _textDescription = MutableLiveData("")
    val textDescription: LiveData<String> = _textDescription

    private val postId = postsRepository.createId()

    fun changeImage(imageUri: Uri) { _image.value = imageUri }
    fun onValueChangeDescription(text: String) { _textDescription.value = text }
    fun onValueChangeName(text: String) { _textName.value = text }
    fun onValueChangeAuthor(text: String) { _textAuthor.value = text }
    fun saveBook() {

        val book = BookPresentation(
            id = postId,
            name = textName.value?:"",
            author = textAuthor.value?:"",
            description = textDescription.value?:"",
            imageUri = getUrlUseCase(postId),
        ).toDomain()

        viewModelScope.launch {
            saveImageUseCase(
                imageUri = image.value.toString(),
                postId = postId
            )
            addPostUseCase(book = book)
        }
    }
}