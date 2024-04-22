package com.chirvi.domain.repository

interface BookReaderRepository {
    suspend fun create(id: String) : List<String>
}