package com.chirvi.domain.repository.auth

interface RegistrationRepository {
    suspend fun registration(
        email: String,
        password: String,
    )
}