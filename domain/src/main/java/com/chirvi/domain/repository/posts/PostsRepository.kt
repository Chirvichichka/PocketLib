package com.chirvi.domain.repository.posts

import com.chirvi.domain.models.BookDomain

interface PostsRepository {
    fun createId() : String
    suspend fun saveBook(book: BookDomain)
    suspend fun getBookById(id: String) : BookDomain?
    suspend fun getAllBooks() : List<BookDomain>
}