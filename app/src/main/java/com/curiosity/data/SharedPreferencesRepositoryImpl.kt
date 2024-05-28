package com.curiosity.data

/**
 * @author matteooriggi
 */

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import com.curiosity.domain.model.Preferences
import com.curiosity.domain.model.User
import com.curiosity.domain.repository.SharedPreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesRepositoryImpl @Inject constructor(
    private val context: Context
): SharedPreferencesRepository{
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("curiosity_user_preferences", Context.MODE_PRIVATE)
    
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

    override suspend fun removeUser() {
        TODO("Not yet implemented")
    }


}