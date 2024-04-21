package com.chirvi.domain.usecase

import com.chirvi.domain.repository.BookReaderRepository

class BookReaderUseCase(private val bookReaderRepository: BookReaderRepository) {
    suspend operator fun invoke() : List<String> {
        return bookReaderRepository.create()
    }
}