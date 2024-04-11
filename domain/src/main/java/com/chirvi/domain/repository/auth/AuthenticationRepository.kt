package com.chirvi.domain.repository.auth

import com.chirvi.domain.models.UserDomain

interface AuthenticationRepository {
    suspend fun authentication(userDomain: UserDomain)
}