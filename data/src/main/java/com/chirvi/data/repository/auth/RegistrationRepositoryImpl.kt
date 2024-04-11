package com.chirvi.data.repository.auth

import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.auth.RegistrationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class RegistrationRepositoryImpl() : RegistrationRepository {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val postsReference = database.getReference("users")

    override suspend fun registration(userDomain: UserDomain) {
        auth.createUserWithEmailAndPassword(
            userDomain.email,
            userDomain.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val currentUser = auth.currentUser
                val userId = currentUser?.uid

                userDomain.id = userId
                saveUser(userDomain = userDomain)
            } else {
                //todo
            }
        }.await()
    }

    private fun saveUser(userDomain: UserDomain) {
        val currentUserId = userDomain.id
        if (currentUserId != null) {
            postsReference.child(currentUserId).setValue(userDomain)
        }
    }
}