package com.curiosity.domain.repository

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.User


interface SharedPreferencesRepository {
    suspend fun saveUser(user: User)

    suspend fun getUser(): User?

    suspend fun removeUser()
}