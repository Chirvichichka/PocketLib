package com.chirvi.domain.models

data class UserDomain(
    var id: String? = null,
    val email: String,
    val password: String,
    val username: String? = null,
    val avatar: String? = null,
    val favorites: List<String> = emptyList()
)