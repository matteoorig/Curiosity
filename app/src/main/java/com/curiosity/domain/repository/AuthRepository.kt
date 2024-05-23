package com.curiosity.domain.repository

/**
 * @author matteooriggi
 */

import com.google.firebase.auth.FirebaseUser

/**
 * Interface for authentication repository.
 *
 * This interface defines the methods related to user authentication.
 */
interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun signOut()
}