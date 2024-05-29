package com.curiosity.data

/**
 * @author matteooriggi
 */

import android.content.Context
import android.content.SharedPreferences
import com.curiosity.domain.model.Preferences
import com.curiosity.domain.model.User
import com.curiosity.domain.repository.SharedPreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of the SharedPreferencesRepository interface.
 *
 * This class provides the implementation of the shared preferences repository
 * using Android's SharedPreferences to persist user data locally.
 *
 * @param context The context used to access SharedPreferences.
 */
@Singleton
class SharedPreferencesRepositoryImpl @Inject constructor(
    private val context: Context
): SharedPreferencesRepository{

    /**
     * Initialize the SharedPreferences instance
     */
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("curiosity_user_preferences", Context.MODE_PRIVATE)

    /**
     * Saves the user data to SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It saves various user attributes to SharedPreferences.
     *
     * @param user The User object containing the data to be saved.
     */
    override suspend fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString("user_uuid", user.uuid)
        editor.putString("user_username", user.username)
        editor.putString("user_email", user.email)
        editor.putString("user_coins", user.coins.toString())
        editor.putString("user_level", user.level.toString())
        editor.putStringSet("user_preferences_values", Preferences.toStringValueList(user.preferences).toMutableSet())
        editor.putStringSet("user_preferences_interest", Preferences.toStringInterestList(user.preferences).toMutableSet())
        editor.apply()
    }

    /**
     * Retrieves the user data from SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It retrieves various user attributes from SharedPreferences.
     *
     * @return The User object containing the retrieved data, or null if not found.
     */
    override suspend fun getUser(): User? {
        val userUuid: String = sharedPreferences.getString("user_uuid", null) ?: return null
        val userUsername: String = sharedPreferences.getString("user_username", null) ?: return null
        val userEmail: String = sharedPreferences.getString("user_email", null) ?: return null
        val userCoins: String = sharedPreferences.getString("user_coins", null) ?: return null
        val userLevel: String = sharedPreferences.getString("user_level", null) ?: return null
        val userPreferencesValues: MutableSet<String> = sharedPreferences.getStringSet("user_preferences_values", null) ?: return null
        val userPreferencesInterest: MutableSet<String> = sharedPreferences.getStringSet("user_preferences_interest", null) ?: return null

        return User(
            uuid = userUuid,
            username = userUsername,
            email = userEmail,
            coins = userCoins.toInt(),
            level = userLevel.toInt(),
            preferences = Preferences.fromStringLists(userPreferencesValues.toList(), userPreferencesInterest.toList())
        )
    }

    /**
     * Removes the user data from SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It removes various user attributes from SharedPreferences if they exist.
     */
    override suspend fun removeUser() {
        val editor = sharedPreferences.edit()
        if(existKey("user_uuid")){
            editor.remove("user_uuid")
            editor.remove("user_username")
            editor.remove("user_email")
            editor.remove("user_coins")
            editor.remove("user_level")
            editor.remove("user_preferences_values")
            editor.remove("user_preferences_interest")
        }
        editor.apply()
    }

    /**
     * Saves the current user's preferences to SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     * It saves the "preferences" field of the current user to SharedPreferences.
     *
     * @param preferences The list of Preferences to be saved for the current user.
     */
    override suspend fun saveCurrentUserPreferences(preferences: List<Preferences>) {
        val editor = sharedPreferences.edit()
        editor.putStringSet("user_preferences_values", Preferences.toStringValueList(preferences).toMutableSet())
        editor.putStringSet("user_preferences_interest", Preferences.toStringInterestList(preferences).toMutableSet())
        editor.apply()
    }

    /**
     * Checks if a specific key exists in SharedPreferences.
     *
     * This method is a suspend function and should be called from a coroutine or another suspend function.
     *
     * @param key The key to check for existence.
     * @return True if the key exists, false otherwise.
     */
    override suspend fun existKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }


}