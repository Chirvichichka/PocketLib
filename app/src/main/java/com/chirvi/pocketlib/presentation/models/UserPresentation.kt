package com.chirvi.pocketlib.presentation.models

import com.chirvi.domain.models.UserDomain

data class UserPresentation(
    val email: String,
    val password: String,
    val username: String? = null,
    val avatar: String? = null
)
fun UserPresentation.toDomain(): UserDomain {
    return UserDomain(
        id = null,
        username = this.username,
        email = this.email,
        password = this.password,
        avatar = this.avatar
    )
}
fun UserDomain.toPresentation(): UserPresentation {
    return UserPresentation(
        username = this.username,
        email = this.email,
        password = this.password,
        avatar = this.avatar
    )
}