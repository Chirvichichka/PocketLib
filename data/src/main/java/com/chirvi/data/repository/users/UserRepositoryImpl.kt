package com.chirvi.data.repository.users

import android.util.Log
import com.chirvi.domain.models.UserDomain
import com.chirvi.domain.repository.users.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl : UserRepository {
    private val database = FirebaseDatabase.getInstance()
    private val usersReference = database.getReference("users")
    private val auth = FirebaseAuth.getInstance()

    override suspend fun registration(userDomain: UserDomain) : String? {
        var error: String? = null
        try {
            val result = auth.createUserWithEmailAndPassword(
                userDomain.email,
                userDomain.password
            ).await()
            val userId = result.user?.uid
            userDomain.id = userId
            saveUser(userDomain)
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            error = e.message
        }
        return error
    }

    override suspend fun authentication(userDomain: UserDomain) {
        auth.signInWithEmailAndPassword(userDomain.email, userDomain.password).await()
    }

    override suspend fun getUser(userId: String): UserDomain {
        val snapshot = usersReference.child(userId).get().await()

        val receivedUser = UserDomain(
            id = snapshot.child("id").value.toString(),
            username = snapshot.child("username").value.toString(),
            email = snapshot.child("email").value.toString(),
            password = snapshot.child("password").value.toString(),
            avatar = snapshot.child("avatar").value.toString(),
            favorites = snapshot.child("favorites").children.map { it.value.toString() },
        )
        return receivedUser
    }

    override suspend fun saveUser(userDomain: UserDomain) {
        val userId = userDomain.id?: " "
        usersReference.child(userId).setValue(userDomain)
    }
}