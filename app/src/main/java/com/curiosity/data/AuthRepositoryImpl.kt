package com.curiosity.data

/**
 * @author matteooriggi
 */

import com.curiosity.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of the AuthRepository interface.
 *
 * This class provides the implementation of the authentication repository
 * using Firebase Authentication.
 *
 * @param firebaseAuth The FirebaseAuth instance used for authentication operations.
 */
open class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override suspend fun signOut() {
        withContext(Dispatchers.IO) {
            firebaseAuth.signOut()
        }
    }

}