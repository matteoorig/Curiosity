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
 * Use case for resetting a user's password.
 *
 * This use case handles the process of resetting a user's password by interacting with the AuthRepository.
 * The state of the operation is managed using Flow<Resource<Boolean>>.
 *
 * @property authRepository The AuthRepository used for authentication operations.
 */
class ResetUserPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Invokes the use case to reset the user's password.
     *
     * This method sends a password reset email to the specified email address using the AuthRepository.
     * The state of the operation is emitted as a Flow of Resource containing a Boolean value.
     *
     * @param email The email address of the user who wants to reset their password.
     * @return A Flow of Resource containing a Boolean value indicating the success or failure of the operation.
     */
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