package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.CoinsState
import com.curiosity.domain.model.Resource
import com.curiosity.domain.model.User
import com.curiosity.domain.repository.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DiscardCuriosityUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
    operator fun invoke(user: MutableStateFlow<User>): Flow<Resource<Boolean>> = flow {
        try {

            emit(Resource.Loading<Boolean>())

            user.value = user.value.copy(
                coins = user.value.coins - CoinsState.DISCARD.value
            )

            // Save current user in sharedPreferences
            sharedPreferencesRepository.saveUser(user.value)


        }catch (e: Exception){
            emit(Resource.Error<Boolean>("DiscardCuriosityUseCase" + e.message))
        }
    }.flowOn(Dispatchers.IO)
}