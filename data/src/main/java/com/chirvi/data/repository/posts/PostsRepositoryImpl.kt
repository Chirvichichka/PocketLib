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
        return suspendCoroutine { continuation ->
            var book: BookDomain
            postsReference.child(id).get()
                .addOnSuccessListener { snapshot ->
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
                    book = BookDomain(
                        id = idBook,
                        author = author,
                        description = description,
                        name = name,
                        genres = genres,
                        image = image,
                    )
                    continuation.resume(book)
            }.addOnFailureListener {
                continuation.resume(null)
            }
        }
    }
//todo поменять
    override suspend fun getAllBooks(): List<BookDomain> {
        return suspendCoroutine { continuation ->
            val booksList = mutableListOf<BookDomain>()

            postsReference.get().addOnSuccessListener { dataSnapshot ->
                for (snapshot in dataSnapshot.children) {
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
                continuation.resume(booksList)
            }.addOnFailureListener { exception ->
                continuation.resume(emptyList())
            }
        }
    }
}