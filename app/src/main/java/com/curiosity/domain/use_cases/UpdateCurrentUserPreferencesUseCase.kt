package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import android.util.Log
import com.curiosity.data.model.Preferences
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateCurrentUserPreferencesUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val dataRepository: DataRepository
) {
    operator fun invoke(preferences: List<Preferences>) : Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val currentUser = repository.currentUser

            if(currentUser == null){
                emit(Resource.Error<Boolean>("InsertCurrentUserPreferencesUseCase no user logged in"))
            }else{

                dataRepository.updateUserPreferences(currentUser.uid, preferences)
                emit(Resource.Success<Boolean>(data = true))
            }

        }catch (e: Exception){
            emit(Resource.Error<Boolean>("InsertCurrentUserPreferencesUseCase " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}