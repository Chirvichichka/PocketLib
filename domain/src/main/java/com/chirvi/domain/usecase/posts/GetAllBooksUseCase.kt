package com.chirvi.domain.usecase.posts

import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository

class GetAllBooksUseCase(private val repository: PostsRepository) {
    suspend operator fun invoke():List<BookDomain> {
        return repository.getAllBooks()
    }
}