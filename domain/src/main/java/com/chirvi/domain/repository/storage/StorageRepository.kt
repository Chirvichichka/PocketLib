package com.chirvi.domain.repository.storage

interface StorageRepository {
    suspend fun saveImage(imageUri: String?, postId: String)
    suspend fun loadImage(id: String) : String
    suspend fun saveBookFile(bookFile: String?, postId: String)
    suspend fun loadBookFile(id: String) : String
}