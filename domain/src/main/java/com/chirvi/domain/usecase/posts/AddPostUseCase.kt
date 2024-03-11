package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.repository.storage.StorageRepository

class AddPostUseCase(private val repository: PostsRepository, ) {
    suspend operator fun invoke(book: BookDomain) {
        repository.saveBook(book = book)
    }
}