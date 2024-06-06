package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.storage.StorageRepository

class SaveBookUseCase(
    private val repository: PostsRepository,
    private val storage: StorageRepository
) {
    suspend operator fun invoke(book: BookDomain) {
        var newBook = book.copy()
        if(book.image != null) {
            storage.saveImage(book.image, book.id)
            newBook = newBook.copy(
                image = storage.loadImage(book.id),
            )
        }
        if (book.bookFile != null) {
            storage.saveBookFile(book.bookFile, book.id)
            newBook = newBook.copy(
                bookFile = storage.loadBookFile(book.id)
            )
        } else {
            newBook = newBook.copy(
                bookFile = storage.loadBookFile("")
            )
        }
        repository.saveBook(book = newBook)
    }
}