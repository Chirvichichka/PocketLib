package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.storage.StorageRepository

class GetAllBooksUseCase(
    private val repository: PostsRepository,
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke():List<BookDomain> {
        val list = repository.getAllBooks()
        val newList = list.map { it.copy(image = storageRepository.loadImage(it.id)) }
        return newList
    }
}