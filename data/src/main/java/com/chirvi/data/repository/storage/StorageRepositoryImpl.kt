package com.chirvi.data.repository.storage

import android.util.Log
import androidx.core.net.toUri
import com.chirvi.domain.repository.storage.StorageRepository
import com.google.firebase.storage.FirebaseStorage

class StorageRepositoryImpl : StorageRepository {

    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.getReference("image/")
    override suspend fun saveImage(imageUri: String, postId: String) {
        val imageReference = storageReference.child("$postId.jpg")
        imageReference.putFile(imageUri.toUri())
    }

    override fun getUrl(id: String) : String {
        val imageReference = storageReference.child("$id.jpg")
        return imageReference.path
    }
    override suspend fun loadImage(postId: String): String  {
        return ""
    }
}