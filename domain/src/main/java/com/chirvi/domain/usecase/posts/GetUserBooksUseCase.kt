package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.storage.StorageRepository

class GetUserBooksUseCase(
    private val postsRepository: PostsRepository,
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke(userId: String) : List<BookDomain> {
        val list = postsRepository.getUserBooks(userId = userId)
        val newList = list.map { it.copy(image = storageRepository.loadImage(it.id)) }
        return newList
    }
}