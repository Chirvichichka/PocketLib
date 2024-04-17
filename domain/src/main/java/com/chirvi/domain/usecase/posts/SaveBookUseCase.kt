package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.storage.StorageRepository

class SaveBookUseCase(
    private val repository: PostsRepository,
    private val storage: StorageRepository
) {
    suspend operator fun invoke(book: BookDomain) {
        if(book.image != null) {
            storage.saveImage(book.image, book.id)
        }
        if (book.bookFile != null) {
            storage.saveBookFile(book.bookFile, book.id)
        }
        val newBook = book.copy(
            image = storage.loadImage(book.id),
            bookFile = storage.loadBookFile(book.id)
        )
        repository.saveBook(book = newBook)
    }
}