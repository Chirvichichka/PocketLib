package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.storage.StorageRepository

class SaveBookUseCase(
    private val repository: PostsRepository,
    private val storage: StorageRepository
) {
    suspend operator fun invoke(book: BookDomain) {
        storage.saveImage(book.image, book.id)
        repository.saveBook(book = book)
    }
}