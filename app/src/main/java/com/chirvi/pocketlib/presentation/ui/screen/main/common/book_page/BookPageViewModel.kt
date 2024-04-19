package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.provider.SyncStateContract
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.posts.GetBookByIdUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

@HiltViewModel
class BookPageViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase
) : ViewModel() {

    private val _book = MutableLiveData(BookPresentation())
    val book: LiveData<BookPresentation> = _book

    fun getBookById(id: String) {
        viewModelScope.launch { suspendGetBookById(id = id) }
    }
    private suspend fun suspendGetBookById(id: String){
        viewModelScope.launch {
            _book.value = getBookByIdUseCase(id)?.toPresentation() ?: BookPresentation()
        }.join()
    }



}
