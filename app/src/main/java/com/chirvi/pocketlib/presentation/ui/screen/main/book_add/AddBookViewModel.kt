package com.chirvi.pocketlib.presentation.ui.screen.main.book_add

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.posts.CreateIdUseCase
import com.chirvi.domain.usecase.posts.SaveBookUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toDomain
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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

    private val _image = MutableLiveData<Uri?>()
    val image: LiveData<Uri?> = _image

    private val _textName = MutableLiveData("")
    val textName: LiveData<String> = _textName

    private val _textAuthor = MutableLiveData("")
    val textAuthor: LiveData<String> = _textAuthor

    private val _textDescription = MutableLiveData("")
    val textDescription: LiveData<String> = _textDescription

    private val _bookFile = MutableLiveData<Uri?>()
    val bookFile: LiveData<Uri?> = _bookFile

    fun loadFileBook(file: Uri) {
        _bookFile.value = file
    }

    private val postId = createIdUseCase()

    private val _opened = MutableLiveData(false)
    val opened: LiveData<Boolean> = _opened

    private val _genres = listOf(
        "Фантастика", "Роман", "Детектив", "Фэнтези", "Триллер",  //todo
        "Мистика", "Приключения", "Драма", "Комедия", "Ужасы",
        "Научная фантастика", "Исторический", "Боевик", "Поэзия",
        "Документальный", "Религия", "Психология", "Философия",
        "Саморазвитие", "Техническая литература", "Публицистика",
        "Биография", "Мемуары", "Путеводитель", "Кулинария",
        "Спорт", "Хобби", "Искусство", "Музыка", "Кино и театр"
    )

    private val _genresWithSelected = MutableLiveData<Map<String, Boolean>>().apply {
        value = _genres.associateWith { false }
    }
    val genresWithSelected: LiveData<Map<String, Boolean>> = _genresWithSelected

    fun toggleSelected(genre: String) {
        val currentMap = _genresWithSelected.value ?: emptyMap()
        val newValue = currentMap.toMutableMap().apply {
            this[genre] = !(this[genre] ?: false)
        }
        _genresWithSelected.value = newValue
    }

    private val _confirmedGenres = MutableLiveData<List<String>>()
    val confirmedGenres: LiveData<List<String>> = _confirmedGenres

    fun confirmGenres() {
        val currentMap = _genresWithSelected.value ?: emptyMap()
        _confirmedGenres.value = currentMap.filterValues { it }.keys.toList()
    }

    fun cancelGenres() {
        val currentMap = _genresWithSelected.value ?: emptyMap()
        val updatedMap = currentMap.mapValues { (_, _) -> false }
        _genresWithSelected.value = updatedMap
        _confirmedGenres.value = updatedMap.filterValues { it }.keys.toList()
    }

    fun deleteImage() { _image.value = null }
    fun openedChange() { _opened.value = !_opened.value!! }
    fun changeImage(imageUri: Uri) { _image.value = imageUri }
    fun onValueChangeDescription(text: String) { _textDescription.value = text }
    fun onValueChangeName(text: String) { _textName.value = text }
    fun onValueChangeAuthor(text: String) { _textAuthor.value = text }
    fun saveBook() { viewModelScope.launch { suspendSaveBook() } }

    fun toFeed(navigationState: NavigationState) {
        navigationState.navigateTo(Screen.Feed.route)
        _state.value = AddBookState.Initial
    }

    private suspend fun suspendSaveBook() {
        _state.value = AddBookState.Loading
        val book = BookPresentation(
            id = postId,
            userId = Firebase.auth.currentUser?.uid?:"",
            name = textName.value?:"",
            author = textAuthor.value?:"",
            description = textDescription.value?:"",
            genres = confirmedGenres.value?: listOf(""),
            image = image.value,
            bookFile = bookFile.value
        ).toDomain()
        viewModelScope.launch{ saveBookUseCase(book = book) }.join()
        _state.value = AddBookState.Saved
    }
}