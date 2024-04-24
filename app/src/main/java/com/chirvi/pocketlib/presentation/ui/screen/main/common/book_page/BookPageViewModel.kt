package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirvi.domain.usecase.posts.GetBookByIdUseCase
import com.chirvi.pocketlib.presentation.models.BookPresentation
import com.chirvi.pocketlib.presentation.models.toPresentation
import com.chirvi.pocketlib.presentation.navigation.Screen
import com.chirvi.pocketlib.presentation.navigation.state.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookPageViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase
) : ViewModel() {
    private val _state = MutableLiveData<BookPageState>(BookPageState.Initial)
    val state: LiveData<BookPageState> = _state

    private val _book = MutableLiveData(BookPresentation())
    val book: LiveData<BookPresentation> = _book

    fun navigateToBack(navigation: NavigationState, currentRoute: String) {
        when(currentRoute) {
            "book_page_feed/{feed_post_id}" -> { navigation.navHostController.popBackStack(inclusive = true, route = Screen.BookFeed.route) }
            "book_page_profile/{profile_post_id}" -> { navigation.navHostController.popBackStack(inclusive = true, route = Screen.BookProfile.route) }
        }
    }

    fun navigateToBookViewer(navigation: NavigationState, currentRoute: String) {
        val bookId = book.value?.id?:""
        when(currentRoute) {
            "book_page_feed/{feed_post_id}" -> { navigation.navigateToBookViewerFromFeed(bookId) }
            "book_page_profile/{profile_post_id}" -> { navigation.navigateToBookViewerFromProfile(bookId) }
        }
    }

    fun getBookById(id: String) {
        BookPageState.Loading
        viewModelScope.launch {
            _book.value = getBookByIdUseCase(id)?.toPresentation() ?: BookPresentation()
            _state.value = BookPageState.Content
        }
    }
}
