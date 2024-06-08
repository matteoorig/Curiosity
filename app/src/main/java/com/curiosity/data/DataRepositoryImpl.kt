package com.curiosity.data

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Preferences
import com.curiosity.domain.model.User
import com.curiosity.domain.repository.DataRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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
    override suspend fun registerUser(user: User): Void? {
        return db.collection("users")
            .document(user.uuid!!)
            .set(
                user.getHashMap()
            ).await()
    }

    /**
     * Retrieves a user document from the Firebase Firestore database.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It retrieves the user document with the specified UUID from the "users" collection in Firestore.
     *
     * @param uuid The unique identifier of the user.
     * @return A DocumentSnapshot containing the user data, or null if the user document does not exist or the query fails.
     */
    override suspend fun getUser(uuid: String): DocumentSnapshot? {
        return db.collection("users").document(uuid).get().await()
    }

    /**
     * Updates the preferences of a user document in the Firebase Firestore database.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It updates the "preferences" field of the user document with the given UUID in the "users" collection.
     *
     * @param uuid The unique identifier of the user.
     * @param preferences The new preferences to update for the user.
     * @return A Void? indicating the result of the update operation. Returns null if the update fails.
     */
    override suspend fun updateUserPreferences(uuid: String, preferences: List<Preferences>): Void? {
        val preferencesHashMap: HashMap<String, Map<String, Any>> = Preferences.getHashMap(preferences)
        return db.collection("users").document(uuid).update(
            "preferences",
            preferencesHashMap
        ).await()
    }

    /**
     * Updates the interval settings of a user document in the Firebase Firestore database.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It updates the "isMinutes" and "interval" fields of the user document with the given UUID in the "users" collection.
     *
     * @param uuid The unique identifier of the user.
     * @param isMinutes A boolean indicating whether the interval is specified in minutes.
     * @param interval The length of the interval.
     * @return A Void? indicating the result of the update operation. Returns null if the update fails.
     */
    override suspend fun updateUserInterval(uuid: String, isMinutes: Boolean, interval: Int): Void? {
        return db.collection("users").document(uuid).update(
            hashMapOf<String, Any>(
                "isMinutes" to isMinutes,
                "interval" to interval.toString()
            )
        ).await()
    }

    /**
     * Loads a list of areas of interest categories from Firestore.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It retrieves the categories from the "areas_of_interest" collection in Firestore.
     *
     * @return A QuerySnapshot containing the result of the query to Firestore.
     * QuerySnapshot is type of firebase object used for manipulate collections.
     */
    override suspend fun loadAreasOfInterestCategories(): QuerySnapshot? {
        return db.collection("areas_of_interest").get().await()
    }

    /**
     * Updates the user's coin count in Firestore.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It updates the number of coins associated with the specified user in the "users" collection in Firestore.
     *
     * @param uuid The unique identifier of the user whose coin count is being updated.
     * @param coins The new number of coins to set for the user.
     * @return A nullable Void indicating the result of the Firestore operation. Returns null if the operation fails.
     */
    override suspend fun updateUserCoins(uuid: String, coins: Int): Void? {
        return db.collection("users").document(uuid).update(
            hashMapOf<String, Any>(
                "coins" to coins,
            )
        ).await()
    }

    /**
     * Updates the level of a user document in the Firebase Firestore database.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It updates the "level" field of the user document with the given UUID in the "users" collection.
     *
     * @param uuid The unique identifier of the user.
     * @param level The new level to update for the user.
     * @return A Void? indicating the result of the update operation. Returns null if the update fails.
     */
    override suspend fun updateUserLevel(uuid: String, level: Int): Void? {
        return db.collection("users").document(uuid).update(
            hashMapOf<String, Any>(
                "level" to level,
            )
        ).await()
    }
}