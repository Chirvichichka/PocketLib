package com.chirvi.data.repository.storage

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.chirvi.domain.repository.storage.StorageRepository
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class StorageRepositoryImpl : StorageRepository {
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.getReference("image/")
    override suspend fun saveImage(imageUri: String?, postId: String) {
        val imageReference: StorageReference = storageReference.child("$postId.jpg")
        if (imageUri != null) {
            imageReference.putFile(imageUri.toUri()).await()
        }
    }
    override suspend fun loadImage(id: String): String  {
        val imageReference = storageReference.child("$id.jpg")
        val downloadUrl: Uri?
        return try {
            downloadUrl = imageReference.downloadUrl.await()
            downloadUrl.toString()
        } catch (e: Exception) {
            ""
        }
    }
}