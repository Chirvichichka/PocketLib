package com.chirvi.domain.usecase.auth

import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.auth.AuthenticationRepository

class AuthenticationUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(userDomain: UserDomain) {
        authenticationRepository.authentication(userDomain = userDomain)
    }
}