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

class NotKnowCuriosityUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    private val dataRepository: DataRepository,
    private val updateUserLevelUseCase: UpdateUserLevelUseCase
) {
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