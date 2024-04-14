package com.chirvi.domain.repository.users

import com.chirvi.domain.models.UserDomain

interface UserRepository {
    suspend fun getUser(userId: String) : UserDomain
    suspend fun saveUser(userDomain: UserDomain)
    suspend fun authentication(userDomain: UserDomain)
    suspend fun registration(userDomain: UserDomain)
}