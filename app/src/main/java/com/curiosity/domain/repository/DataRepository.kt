package com.curiosity.domain.repository

/**
 * @author matteooriggi
 */

import com.curiosity.data.model.User
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

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
    suspend fun registerUser(user: User): Void?

    suspend fun getUser(uuid: String): DocumentSnapshot?

    // suspend fun updateUserPreferences(uuid: String, )
    /**
     * Loads a list of areas of interest categories from Firestore.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It retrieves the categories from the "areas_of_interest" collection in Firestore.
     *
     * @return A QuerySnapshot containing the result of the query to Firestore.
     * QuerySnapshot is type of firebase object used for manipulate collections.
     */
    suspend fun loadAreasOfInterestCategories(): QuerySnapshot?

}