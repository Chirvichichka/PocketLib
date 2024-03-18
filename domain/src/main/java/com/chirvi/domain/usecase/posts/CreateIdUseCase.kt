package com.chirvi.domain.usecase.posts

import com.chirvi.domain.repository.posts.PostsRepository

class CreateIdUseCase(private val repository: PostsRepository) {
    operator fun invoke() : String {
        return repository.createId()
    }
}