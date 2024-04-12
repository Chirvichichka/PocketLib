package com.chirvi.data.repository.posts

import android.util.Log
import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PostsRepositoryImpl : PostsRepository {
    private val database = FirebaseDatabase.getInstance()
    private val postsReference = database.getReference("posts")

    override fun createId(): String { return postsReference.push().key ?: "" }

    override suspend fun saveBook(book: BookDomain) {
        postsReference.child(book.id).setValue(book).await()
    }
    override suspend fun getBookById(id: String): BookDomain? {
        return try {
            val snapshot = postsReference.child(id).get().await()
            val idBook = snapshot.child("id").value.toString()
            val author = snapshot.child("author").value.toString()
            val description = snapshot.child("description").value.toString()
            val name = snapshot.child("name").value.toString()
            val genres = mutableListOf<String>()
            val genresSnapshot = snapshot.child("genres")
            for (genreSnapshot in genresSnapshot.children) {
                val genre = genreSnapshot.value.toString()
                genres.add(genre)
            }
            val image = snapshot.child("image").value.toString()
            BookDomain(
                id = idBook,
                author = author,
                description = description,
                name = name,
                genres = genres,
                image = image,
            )
        } catch (e: Exception) {
            null
        }
    }

//todo поменять
override suspend fun getAllBooks(): List<BookDomain> {
    return try {
        val dataSnapshot = postsReference.get().await()
        val booksList = mutableListOf<BookDomain>()
        for (snapshot in dataSnapshot.children.reversed()) {
            val id = snapshot.child("id").value.toString()
            val author = snapshot.child("author").value.toString()
            val description = snapshot.child("description").value.toString()
            val name = snapshot.child("name").value.toString()
            val genres = mutableListOf<String>()
            val genresSnapshot = snapshot.child("genres")
            for (genreSnapshot in genresSnapshot.children) {
                val genre = genreSnapshot.value.toString()
                genres.add(genre)
            }
            val image = snapshot.child("image").value.toString()
            val book = BookDomain(
                id = id,
                author = author,
                description = description,
                name = name,
                genres = genres,
                image = image
            )
            booksList.add(book)
        }
        booksList
    } catch (e: Exception) {
        emptyList()
    }
    }
}