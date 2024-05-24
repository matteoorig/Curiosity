package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for checking the existence of the current authenticated user.
 *
 * This use case interacts with the AuthRepository to check if there is a currently
 * authenticated user. It returns a Flow of Resource<Boolean> indicating the loading,
 * success, or error state of the operation.
 *
 * @param repository The AuthRepository used for authentication operations.
 */
class ExistCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository,
    // prima era private val repository: AuthRepositoryImpl,
) {
    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val currentUser = repository.currentUser

            if(currentUser != null){
                emit(Resource.Success<Boolean>(data = true))
            }else{
                emit(Resource.Success<Boolean>(data = false))
            }
        }catch (e: Exception){
            emit(Resource.Error<Boolean>("ExistCurrentUserUseCase" + e.message))
        }
    }.flowOn(Dispatchers.IO)
}