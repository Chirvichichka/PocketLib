package com.chirvi.data.repository.auth

import com.chirvi.domain.repository.auth.RegistrationRepository
import com.google.firebase.auth.FirebaseAuth

class RegistrationRepositoryImpl(
    private val database: FirebaseAuth
) : RegistrationRepository {
    override suspend fun registration(email: String, password: String) {
        database.createUserWithEmailAndPassword(
            email,
            password
        )
    }
}