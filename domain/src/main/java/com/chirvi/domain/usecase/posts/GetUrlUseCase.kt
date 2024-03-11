package com.chirvi.domain.usecase.posts

import com.chirvi.domain.repository.storage.StorageRepository

class GetUrlUseCase(private val storage: StorageRepository) {
    operator fun invoke(id: String) : String {
        return storage.getUrl(id = id)
    }
}