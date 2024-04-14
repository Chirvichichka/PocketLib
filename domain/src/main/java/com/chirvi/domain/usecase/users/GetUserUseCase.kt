package com.chirvi.domain.usecase.users

import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.users.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: String) : UserDomain {
        return userRepository.getUser(userId = userId)
    }
}