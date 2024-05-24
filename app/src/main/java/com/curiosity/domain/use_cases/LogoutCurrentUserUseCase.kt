package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogoutCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    operator fun invoke(): Flow<Resource<AuthResult>> = flow {
        try {
            emit(Resource.Loading<AuthResult>())
            val currentUser = repository.currentUser

            if(currentUser != null){
                repository.signOut()
                emit(Resource.Success<AuthResult>())
            }else{
                emit(Resource.Error<AuthResult>("LogoutCurrentUserUseCase " + "No users are logged in"))
            }
        }catch (e: Exception){
            emit(Resource.Error<AuthResult>("LogoutCurrentUserUseCase " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}
