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

    /**
     * Gets the currently authenticated user.
     *
     * @return The currently authenticated FirebaseUser, or null if there is no authenticated user.
     */
    val currentUser: FirebaseUser?

    /**
     * Signs out the currently authenticated user.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It performs the sign-out operation on the IO dispatcher to avoid blocking the main thread.
     */
    suspend fun signOut()

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
    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthResult

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
    suspend fun signInUserWithEmailAndPassword(email: String, password: String): AuthResult

    /**
     * Sends a password reset email to the specified email address.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It performs the password reset operation on the IO dispatcher to avoid blocking the main thread.
     *
     * @param email The email address of the user who wants to reset their password.
     */
    suspend fun resetUserPassword(email: String)

}