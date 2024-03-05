package com.chirvi.data.repository.posts

import com.chirvi.domain.models.Book
import com.chirvi.domain.repository.posts.PostsRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

class PostsRepositoryImpl : PostsRepository {
    private val database = FirebaseDatabase.getInstance().reference.child("posts")

    override suspend fun getAllBooks(): List<Book> {
        val booksList = mutableListOf<Book>()
        val dataSnapshot = database.get().await()
        for (snapshot in dataSnapshot.children) {
            val book = snapshot.getValue(Book::class.java)
            book?.let { booksList.add(it) }
        }
        return booksList
    }

    override suspend fun getBookById(id: Int): Book? {
        val dataSnapshot = database.child(id.toString()).get().await()
        return dataSnapshot.getValue(Book::class.java)
    }
}
