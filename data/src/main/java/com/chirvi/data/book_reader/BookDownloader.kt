package com.chirvi.data.book_reader

import android.content.Context
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.FileOutputStream

class BookDownloader(private val context: Context) {
    private val storage = FirebaseStorage.getInstance()
    private val bookStorageReference = storage.getReference("book/")

    suspend fun downloadBook(id: String): File? {
        val storageReference = bookStorageReference.child("$id.epub") // Путь к файлу в Firebase Storage

        val tempFile = File(context.filesDir, "book_temp.epub")

        return try {
            val fileOutputStream = FileOutputStream(tempFile)
            storageReference.getFile(tempFile).await()
            fileOutputStream.close()
            tempFile
        } catch (e: Exception) {
            Log.e("BookDownloader", "Error downloading book", e)
            null
        }
    }
}