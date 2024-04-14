package com.chirvi.domain.usecase.users

import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.users.UserRepository

class RegistrationUseCase(
    private val userRepository: UserRepository,
    ) {
    suspend operator fun invoke(
        user: UserDomain
    ): String {
        try {
            userRepository.registration(user)
            return ""
        } catch (e: Exception) {
            return e.localizedMessage
        }
    }
}