package com.chirvi.domain.usecase.users

import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.storage.StorageRepository
import com.chirvi.domain.repository.users.UserRepository

class SaveUserUseCase(
    private val userRepository: UserRepository,
    private val storage: StorageRepository
) {
    suspend operator fun invoke(userDomain: UserDomain) {
        if(userDomain.avatar != null) {
            storage.saveImage(userDomain.avatar, userDomain.id?:"")
        }
        val userWithAvatar = userDomain.copy(
            avatar = storage.loadImage(userDomain.id?:"")
        )
        userRepository.saveUser(userWithAvatar)
    }
}