package com.chirvi.data.repository.books

import com.chirvi.data.data_base.dao.BookDao
import com.chirvi.data.data_base.entities.Book

class BooksRepository(private val bookDao: BookDao) {
    suspend fun getAllTasks(): List<Book> {
        return bookDao.getAllBooks()
    }

    suspend fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }
}