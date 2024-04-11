package com.chirvi.domain.repository.auth

import com.chirvi.domain.models.UserDomain

interface RegistrationRepository {
    suspend fun registration(userDomain: UserDomain)
}