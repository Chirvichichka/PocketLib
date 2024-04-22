package com.chirvi.data.repository

import android.content.Context
import android.util.Log
import com.chirvi.data.R
import com.chirvi.data.book_reader.BookReader
import com.chirvi.domain.repository.BookReaderRepository
import java.io.File

class BookReaderRepositoryImpl(private val context: Context) : BookReaderRepository {
    override suspend fun create(id: String): List<String> {
        return BookReader(context = context).create(id)
    }
}