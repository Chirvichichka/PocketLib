package com.chirvi.data.repository.auth

import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.auth.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthenticationRepositoryImpl : AuthenticationRepository {
    private val auth = FirebaseAuth.getInstance()

    override suspend fun authentication(userDomain: UserDomain) {
        auth.signInWithEmailAndPassword(userDomain.email, userDomain.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                } else {
                    //todo
                }
            }.await()
    }
}