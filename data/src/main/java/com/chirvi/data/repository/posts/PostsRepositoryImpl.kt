package com.chirvi.data.repository.posts

import android.util.Log
import androidx.core.net.toUri
import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.chirvi.domain.usecase.posts.GetUrlUseCase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class PostsRepositoryImpl : PostsRepository {
    private val database = FirebaseDatabase.getInstance()
    private val postsReference = database.getReference("posts")
    override fun createId(): String {
        return postsReference.push().key?: ""
    }

    override suspend fun saveBook(book: BookDomain) {
        postsReference.child(book.id).setValue(book)
            .addOnSuccessListener {
                Log.e("AAA", "Data saved successfully")
            }
            .addOnFailureListener { error ->
                Log.e("AAA", "Error saving data: ${error.message}")
            }
    }

    override suspend fun loadBookById(id: String): BookDomain? {
        var book: BookDomain? = null
        postsReference.child(id).get().addOnSuccessListener {
            val idBook = it.child("id").value.toString()
            val author = it.child("author").value.toString()
            val description = it.child("description").value.toString()
            val name = it.child("name").value.toString()
            val image = it.child("imageUri").value.toString()
            book = BookDomain(
                id = idBook,
                author = author,
                description = description,
                name = name,
                imageUri = image,
            )
        }
        return book
    }
}