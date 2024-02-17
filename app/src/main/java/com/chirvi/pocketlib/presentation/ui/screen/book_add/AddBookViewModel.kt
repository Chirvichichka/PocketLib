package com.chirvi.pocketlib.presentation.ui.screen.book_add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor() : ViewModel() {

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textAuthor = MutableLiveData("")
    val textAuthor: LiveData<String> = _textAuthor

    private val _textDescription = MutableLiveData("")
    val textDescription: LiveData<String> = _textDescription
    fun onValueChangeDescription(text: String) { _textDescription.value = text }

    fun onValueChangeName(text: String) { _textName.value = text }
    fun onValueChangeAuthor(text: String) { _textAuthor.value = text }

}