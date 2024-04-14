package com.chirvi.data.repository.posts

import android.util.Log
import com.chirvi.domain.models.BookDomain
import com.chirvi.domain.repository.posts.PostsRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class PostsRepositoryImpl : PostsRepository {
    private val database = FirebaseDatabase.getInstance()
    private val postsReference = database.getReference("posts")

    override fun createId(): String { return postsReference.push().key ?: "" }

    override suspend fun saveBook(book: BookDomain) {
        postsReference.child(book.id).setValue(book).await()
    }

    override suspend fun getBookById(id: String): BookDomain {
        val snapshot = postsReference.child(id).get().await()
        val book = BookDomain(
            id = snapshot.child("id").value.toString(),
            userId = snapshot.child("userId").value.toString(),
            name = snapshot.child("name").value.toString(),
            author = snapshot.child("author").value.toString(),
            description =  snapshot.child("description").value.toString(),
            genres = snapshot.child("genres").children.map { it.value.toString() },
            image = snapshot.child("image").value.toString()
        )
        return book
    }

    override suspend fun getAllBooks(): List<BookDomain> {
        val dataSnapshot = postsReference.get().await()
        val booksList = mutableListOf<BookDomain>()
        for (snapshot in dataSnapshot.children.reversed()) {
            booksList.add(
                BookDomain(
                    id = snapshot.child("id").value.toString(),
                    userId = snapshot.child("userId").value.toString(),
                    name = snapshot.child("name").value.toString(),
                    author = snapshot.child("author").value.toString(),
                    description =  snapshot.child("description").value.toString(),
                    genres = snapshot.child("genres").children.map { it.value.toString() },
                    image = snapshot.child("image").value.toString()
                )
            )
        }
        return booksList
    }

    override suspend fun getUserBooks(userId: String): List<BookDomain> {
        val query = postsReference.orderByChild("userId").equalTo(userId)
        val dataSnapshot = query.get().await()
        val userBookList = mutableListOf<BookDomain>()
        for (snapshot in dataSnapshot.children.reversed()) {
            userBookList.add(
                BookDomain(
                    id = snapshot.child("id").value.toString(),
                    userId = snapshot.child("userId").value.toString(),
                    name = snapshot.child("name").value.toString(),
                    author = snapshot.child("author").value.toString(),
                    description =  snapshot.child("description").value.toString(),
                    genres = snapshot.child("genres").children.map { it.value.toString() },
                    image = snapshot.child("image").value.toString()
                )
            )
        }
        return userBookList
    }
}