package com.curiosity.data

/**
 * @author matteooriggi
 */

import com.curiosity.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of the AuthRepository interface.
 *
 * This class provides the implementation of the authentication repository
 * using Firebase Authentication.
 *
 * @param firebaseAuth The FirebaseAuth instance used for authentication operations.
 */
@Singleton
open class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    /**
     * Gets the currently authenticated user.
     *
     * @return The currently authenticated FirebaseUser, or null if there is no authenticated user.
     */
    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    /**
     * Signs out the currently authenticated user.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It performs the sign-out operation on the IO dispatcher to avoid blocking the main thread.
     */
    override suspend fun signOut() {
        withContext(Dispatchers.IO) {
            firebaseAuth.signOut()
        }
    }

    /**
     * Creates a new user with the specified email and password.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It performs the create user operation on the IO dispatcher to avoid blocking the main thread.
     *
     * @param email The email address of the new user.
     * @param password The password of the new user.
     * @return An AuthResult containing the result of the create user operation.
     */
    override suspend fun createUserWithEmailAndPassword(
        email: String, password: String
    ): AuthResult {
        return withContext(Dispatchers.IO) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }
    }

    /**
     * Signs in an existing user with the specified email and password.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It performs the sign-in operation on the IO dispatcher to avoid blocking the main thread.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return An AuthResult containing the result of the sign-in operation.
     */
    override suspend fun signInUserWithEmailAndPassword(
        email: String, password: String
    ): AuthResult {
        return withContext(Dispatchers.IO) {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }
    }
}