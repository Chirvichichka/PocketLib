package com.chirvi.domain.repository.posts

import com.chirvi.domain.models.Book

interface PostsRepository {
    suspend fun getAllBooks() : List<Book>
    suspend fun getBookById(id: Int) : Book?
}