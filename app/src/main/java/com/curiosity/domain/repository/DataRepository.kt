package com.curiosity.domain.repository

/**
 * @author matteooriggi
 */

import com.google.firebase.firestore.FirebaseFirestore

/**
 * Interface for data repository operations.
 *
 * This interface defines the methods related to data operations, including
 * registering a user in the Firebase Firestore database.
 *
 * @property db The FirebaseFirestore instance used for database operations.
 */
interface DataRepository {

    /**
     * The FirebaseFirestore instance used for database operations.
     */
    val db: FirebaseFirestore

    /**
     * Registers a user in the Firebase Firestore database.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It performs the registration operation by adding a new user document with the specified UUID, username, email, and password.
     *
     * @param uuid The unique identifier for the user.
     * @param username The username of the user.
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return A Void? indicating the result of the registration operation.
     */
    suspend fun registerUser(uuid: String, username: String, email: String, password: String): Void?

}