package com.chirvi.domain.usecase.posts

import com.chirvi.domain.repository.storage.StorageRepository

class LoadImageUseCase(private val storage: StorageRepository ) {
    suspend operator fun invoke(postId: String) : String {
       return storage.loadImage(postId = postId)
    }
}