package com.chirvi.pocketlib.presentation.models

import com.chirvi.domain.models.BookDomain

data class BookPresentation(
    val id: String,
    val name: String,
    val author: String,
    val description: String,
    val imageUri: String
)
fun BookPresentation.toDomain(): BookDomain {
    return BookDomain(
        id = this.id,
        name = this.name,
        author = this.author,
        description = this.description,
        imageUri = this.imageUri
    )
}
fun BookDomain.toPresentation(): BookPresentation {
    return BookPresentation(
        id = this.id,
        name = this.name,
        author = this.author,
        description = this.description,
        imageUri = this.imageUri
    )
}