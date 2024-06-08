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

class ResetUserPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String): Flow<Resource<Boolean>> = flow {

        try {
            emit(Resource.Loading<Boolean>())

            authRepository.resetUserPassword(email)

            emit(Resource.Success<Boolean>())

        }catch (e: Exception){
            emit(Resource.Error<Boolean>("ResetUserPasswordUseCase" + e.message))
        }
    }.flowOn(Dispatchers.IO)
}