package com.chirvi.domain.usecase.auth

import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.auth.RegistrationRepository

class RegistrationUseCase(private val repository: RegistrationRepository) {
    suspend operator fun invoke (
        user: UserDomain
    ): String {
        try {
            repository.registration(user)
            return ""
        } catch (e: Exception) {
            return e.localizedMessage
        }
    }
}