package com.chirvi.domain.repository

interface BookReaderRepository {
    suspend fun create() : List<String>
}