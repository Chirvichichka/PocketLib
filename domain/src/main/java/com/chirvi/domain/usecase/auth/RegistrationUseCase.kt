package com.chirvi.domain.usecase.auth

import com.chirvi.domain.repository.auth.RegistrationRepository

class RegistrationUseCase(private val repository: RegistrationRepository) {
    suspend operator fun invoke (
        email: String,
        password: String
    ): String {
        try {
            repository.registration(
                email = email,
                password = password,
            )
            return ""
        } catch (e: Exception) {
            return e.localizedMessage
        }
    }
}