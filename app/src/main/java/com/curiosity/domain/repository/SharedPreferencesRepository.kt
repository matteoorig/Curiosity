package com.curiosity.domain.repository

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.IntervalNotification
import com.curiosity.domain.model.Preferences
import com.curiosity.domain.model.User

interface SharedPreferencesRepository {

    /**
     * Saves the user data to SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It saves various user attributes to SharedPreferences.
     *
     * @param user The User object containing the data to be saved.
     */
    suspend fun saveUser(user: User)

    /**
     * Retrieves the user data from SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It retrieves various user attributes from SharedPreferences.
     *
     * @return The User object containing the retrieved data, or null if not found.
     */
    suspend fun getUser(): User?

    /**
     * Removes the user data from SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It removes various user attributes from SharedPreferences if they exist.
     */
    suspend fun removeUser()

    /**
     * Checks if a specific key exists in SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     *
     * @param key The key to check for existence.
     * @return True if the key exists, false otherwise.
     */
    suspend fun existKey(key: String): Boolean


    /**
     * Saves the current user's preferences to SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It saves the "preferences" field of the current user to SharedPreferences.
     *
     * @param preferences The list of Preferences to be saved for the current user.
     */
    suspend fun saveCurrentUserPreferences(preferences: List<Preferences>)

    suspend fun saveCurrentUserCoins(coins: Int)

    /**
     * Saves the current user's interval and its relative type to SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It saves the "isMinutes" and "interval" fields of the current user to SharedPreferences.
     *
     * @param isMinutes The boolean value if the interval is in minutes or in hours.
     * @param interval The interval time value between notification.
     */
    suspend fun saveCurrentUserInterval(isMinutes: Boolean, interval: Int)

    fun getInterval(): IntervalNotification?
}