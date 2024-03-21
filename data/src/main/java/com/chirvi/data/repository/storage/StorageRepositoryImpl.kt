package com.chirvi.data.repository.storage

import android.util.Log
import androidx.core.net.toUri
import com.chirvi.domain.repository.storage.StorageRepository
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class StorageRepositoryImpl : StorageRepository {
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.getReference("image/")
    override suspend fun saveImage(imageUri: String, postId: String) {
        val imageReference: StorageReference = storageReference.child("$postId.jpg")
        imageReference.putFile(imageUri.toUri())
    }
    override suspend fun loadImage(id: String): String  {
        val imageReference = storageReference.child("$id.jpg")
        val downloadUrl = imageReference.downloadUrl.await()
        return downloadUrl.toString()
    }
}