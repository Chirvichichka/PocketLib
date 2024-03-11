package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository

class LoadBookByIdUseCase(private val repository: PostsRepository) {
    suspend operator fun invoke(id: String) : BookDomain? {
        return repository.loadBookById(id)
    }
}