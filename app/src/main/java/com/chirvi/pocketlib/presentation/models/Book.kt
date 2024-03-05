package com.chirvi.pocketlib.presentation.models

import com.chirvi.domain.models.Book
import com.chirvi.pocketlib.R

data class BookPresentation(
    val id: Int,
    val name: String,
    val author: String,
    val description: String
)

object BookItemMapper {
    fun fromDomain(book: Book): BookPresentation {
        return BookPresentation(
            id = book.id,
            name = book.name,
            author = book.author,
            description = book.description
        )
    }
}