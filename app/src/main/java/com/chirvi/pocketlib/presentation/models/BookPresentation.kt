package com.chirvi.pocketlib.presentation.models

import android.net.Uri
import androidx.core.net.toUri
import com.chirvi.domain.models.BookDomain

data class BookPresentation(
    val id: String = "",
    val userId: String = "",
    val name: String = "",
    val author: String = "",
    val description: String = "",
    val genres: List<String> = emptyList(),
    val image: Uri? = null,
    val bookFile: Uri? = null
)
fun BookPresentation.toDomain(): BookDomain {
    return BookDomain(
        id = this.id,
        userId = this.userId,
        name = this.name,
        author = this.author,
        description = this.description,
        genres = this.genres,
        image = if(this.image != null) { this.image.toString() } else { null },
        bookFile = if(this.bookFile != null) { this.bookFile.toString() } else { null },
    )
}
fun BookDomain.toPresentation(): BookPresentation {
    return BookPresentation(
        id = this.id,
        userId = this.userId,
        name = this.name,
        author = this.author,
        description = this.description,
        genres = this.genres,
        image = this.image?.toUri(),
        bookFile = this.bookFile?.toUri()
    )
}