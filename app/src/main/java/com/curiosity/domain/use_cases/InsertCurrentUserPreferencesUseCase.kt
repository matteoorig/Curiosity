package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertCurrentUserPreferencesUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val dataRepository: DataRepository
) {
    operator fun invoke() : Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val currentUser = repository.currentUser

            if(currentUser == null){
                emit(Resource.Error<Boolean>("InsertCurrentUserPreferencesUseCase no user logged in"))
            }else{

            }

        }catch (e: Exception){
            emit(Resource.Error<Boolean>("InsertCurrentUserPreferencesUseCase " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}