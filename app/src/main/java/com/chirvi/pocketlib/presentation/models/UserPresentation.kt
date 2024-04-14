package com.chirvi.pocketlib.presentation.models

import android.net.Uri
import androidx.core.net.toUri
import com.chirvi.domain.models.UserDomain

data class UserPresentation(
    val email: String,
    val password: String,
    val username: String? = null,
    val avatar: Uri? = null
)
fun UserPresentation.toDomain(): UserDomain {
    return UserDomain(
        id = null,
        username = this.username,
        email = this.email,
        password = this.password,
        avatar = if(this.avatar != null) { this.avatar.toString() } else { null }
    )
}
fun UserDomain.toPresentation(): UserPresentation {
    return UserPresentation(
        username = this.username,
        email = this.email,
        password = this.password,
        avatar = this.avatar?.toUri()
    )
}