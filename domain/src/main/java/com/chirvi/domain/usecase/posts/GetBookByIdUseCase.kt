package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.storage.StorageRepository

class GetBookByIdUseCase(
    private val repository: PostsRepository,
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke(id: String) : BookDomain? {
        val book = repository.getBookById(id)
        val newBook = book?.copy(
            image = storageRepository.loadImage(book.id),
            bookFile = storageRepository.loadBookFile(book.id)
        )
        return newBook
    }
}