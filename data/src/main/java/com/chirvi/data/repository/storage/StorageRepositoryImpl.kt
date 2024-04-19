package com.chirvi.data.repository.storage

import androidx.core.net.toUri
import com.chirvi.domain.repository.storage.StorageRepository
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class StorageRepositoryImpl : StorageRepository {
    private val storage = FirebaseStorage.getInstance()
    private val imageStorageReference = storage.getReference("image/")
    private val bookStorageReference = storage.getReference("book/")

    override suspend fun saveImage(imageUri: String?, postId: String) {
        if (imageUri != null) {
            val imageReference: StorageReference = imageStorageReference.child("$postId.jpg")
            val uri = imageUri.toUri()
            imageReference.putFile(uri).await()
        }
    }
    override suspend fun loadImage(id: String): String  {
        val imageReference = imageStorageReference.child("$id.jpg")
        return try {
            imageReference.downloadUrl.await().toString()
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun saveBookFile(bookFile: String?, postId: String) {
        if (bookFile != null) {
            val bookStorageReference: StorageReference = bookStorageReference.child("$postId.pdf")
            val uri = bookFile.toUri()
            bookStorageReference.putFile(uri).await()
        }
    }

    override suspend fun loadBookFile(id: String): String {
        val bookFileReference = bookStorageReference.child("$id.pdf")
        return try {
            bookFileReference.downloadUrl.await().toString()
        } catch (e: Exception) {
            ""
        }
    }
}