package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.CoinsState
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
 * Use case for handling the event when a user does not know a curiosity.
 *
 * This use case updates the user's coins and level both locally (in SharedPreferences) and remotely (in Firestore).
 * It interacts with the SharedPreferencesRepository, DataRepository and UpdateUserLevelUseCase.
 * The state of the operation is managed using Flow<Resource<User>>.
 *
 * @property sharedPreferencesRepository The repository used for managing SharedPreferences operations.
 * @property dataRepository The repository used for managing Firestore operations.
 * @property updateUserLevelUseCase The use case for updating the user's level.
 */
class NotKnowCuriosityUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    private val dataRepository: DataRepository,
    private val updateUserLevelUseCase: UpdateUserLevelUseCase
) {

    /**
     * Invokes the use case when the user does not know a curiosity.
     *
     * This method updates the user's coins, saves the updated coins to SharedPreferences,
     * updates the coins in Firestore, and then updates the user's level both locally and remotely.
     *
     * @param user A MutableStateFlow containing the current user.
     * @return A Flow of Resource containing the updated user.
     */
    operator fun invoke(user: MutableStateFlow<User>): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())

            user.value = user.value.copy(
                coins = user.value.coins + CoinsState.VICTORY.value
            )

            // Save current user in sharedPreferences
            sharedPreferencesRepository.saveCurrentUserCoins(user.value.coins)

            // Update user coins in Firestore
            dataRepository.updateUserCoins(user.value.uuid!!, user.value.coins)

            // Update user level in Firestore and SharedPreferences
            updateUserLevelUseCase(user).collect { result ->
                when (result) {
                    is Resource.Success -> emit(Resource.Success<User>(data = result.data))
                    is Resource.Error -> emit(Resource.Error<User>("NotKnowCuriosityUseCase: " + result.message))
                    is Resource.Loading -> emit(Resource.Loading<User>())
                }
            }

        }catch (e: Exception){
            emit(Resource.Error<User>("NotKnowCuriosityUseCase" + e.message))
        }
    }.flowOn(Dispatchers.IO)
}