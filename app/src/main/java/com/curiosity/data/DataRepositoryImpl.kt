package com.curiosity.data

/**
 * @author matteooriggi
 */

import com.curiosity.data.model.User
import com.curiosity.domain.repository.DataRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of the DataRepository interface.
 *
 * This class provides the implementation of DataRepository operations using Firebase Firestore.
 *
 * @param firestore The FirebaseFirestore instance used for database operations.
 */
@Singleton
open class DataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): DataRepository{

    /**
     * The FirebaseFirestore instance used for database operations.
     */
    override val db: FirebaseFirestore get() = firestore

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
    override suspend fun registerUser(uuid: String, username: String, email: String, password: String): Void? {
        val userData: HashMap<String, Any> = User(uuid = uuid, username = username, email= email, password = password).getHashMap()
        return db.collection("users")
            .document(uuid)
            .set(userData).await()
    }
}