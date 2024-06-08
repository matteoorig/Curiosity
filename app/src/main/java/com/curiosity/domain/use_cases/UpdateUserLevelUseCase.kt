package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import android.util.Log
import com.curiosity.domain.model.Resource
import com.curiosity.domain.model.User
import com.curiosity.domain.repository.DataRepository
import com.curiosity.domain.repository.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for updating the user's level based on their current coin count.
 *
 * This use case handles the process of updating the user's level both locally (in SharedPreferences) and remotely (in Firestore).
 * The level is determined based on the user's current coin count. The state of the operation is managed using Flow<Resource<User>>.
 *
 * @property dataRepository The DataRepository used for database operations.
 * @property sharedPreferencesRepository The SharedPreferencesRepository used for saving user data locally.
 */
class UpdateUserLevelUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
    /**
     * Invokes the use case to update the user's level.
     *
     * This method updates the user's level based on their current coin count, saves the updated level to SharedPreferences,
     * and updates the level in Firestore. The state of the operation is emitted as a Flow of Resource containing the updated User.
     *
     * @param user A MutableStateFlow containing the current user.
     * @return A Flow of Resource containing the updated User.
     */
    operator fun invoke(user: MutableStateFlow<User>): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())

            // Based on the range of coins I assign the correct level to the user
            val updatedLevel: Int = when(user.value.coins) {
                in 0 .. 99 -> 1
                in 100 .. 199 -> 2
                in 200 .. 399 -> 3
                in 400 .. 699 -> 4
                in 700 .. 999 -> 5
                else -> 6
            }
            user.value.level = updatedLevel
            dataRepository.updateUserLevel(user.value.uuid!!, updatedLevel)

            sharedPreferencesRepository.saveCurrentUserLevel(updatedLevel)


            emit(Resource.Success<User>(data = user.value))
        }catch (e: Exception){
            emit(Resource.Error<User>("UpdateUserLevelUseCase" + e.message))
        }
    }.flowOn(Dispatchers.IO)
}