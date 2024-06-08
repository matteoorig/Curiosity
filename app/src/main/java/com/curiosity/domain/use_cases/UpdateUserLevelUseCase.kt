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

class UpdateUserLevelUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
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