package com.chirvi.domain.usecase.users

import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.users.UserRepository

class AuthenticationUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userDomain: UserDomain) {
        userRepository.authentication(userDomain = userDomain)
    }
}