package com.chirvi.pocketlib.presentation.models

import com.chirvi.domain.models.BookDomain

data class BookPresentation(
    val id: String,
    val name: String,
    val author: String,
    val description: String,
    val image: String? = null
)
fun BookPresentation.toDomain(): BookDomain {
    return BookDomain(
        id = this.id,
        name = this.name,
        author = this.author,
        description = this.description,
        image = this.image
    )
}
fun BookDomain.toPresentation(): BookPresentation {
    return BookPresentation(
        id = this.id,
        name = this.name,
        author = this.author,
        description = this.description,
        image = this.image
    )
}