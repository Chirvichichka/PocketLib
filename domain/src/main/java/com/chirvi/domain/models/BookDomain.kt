package com.chirvi.domain.models

data class BookDomain(
    val id: String,
    val userId: String,
    val name: String,
    val author: String,
    val description: String,
    val genres: List<String>,
    val image: String? = null,
)