package com.chirvi.domain.usecase.posts

import com.chirvi.domain.repository.storage.StorageRepository

class SaveImageUseCase(private val storage: StorageRepository) {
    suspend operator fun invoke(
        imageUri: String,
        postId: String
    ) {
        storage.saveImage(imageUri = imageUri, postId = postId)
    }
}