package com.curiosity.domain.repository

/**
 * @author matteooriggi
 */

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

/**
 * Interface for authentication repository.
 *
 * This interface defines the methods related to user authentication.
 */
interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun signOut()

    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthResult

    suspend fun signInUserWithEmailAndPassword(email: String, password: String): AuthResult

    suspend fun resetUserPassword(email: String)

}