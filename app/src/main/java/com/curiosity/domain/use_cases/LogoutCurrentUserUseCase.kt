package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.data.SharedPreferencesRepositoryImpl
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for logging out the current user.
 *
 * This use case handles the process of logging out the currently authenticated user.
 * It interacts with the AuthRepository to perform the sign-out operation. The state
 * of the operation is managed using Flow<Resource<AuthResult>>.
 *
 * @param repository The AuthRepository used for authentication operations.
 */
class LogoutCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val sharedPreferencesRepositoryImpl: SharedPreferencesRepositoryImpl
) {

    /**
     * Invokes the use case to log out the current user.
     *
     * This method performs the sign-out operation using the AuthRepository and removes the user data
     * from SharedPreferences. The state of the operation is emitted as a Flow of Resource containing AuthResult.
     *
     * @return A Flow of Resource containing AuthResult.
     */
    operator fun invoke(): Flow<Resource<AuthResult>> = flow {
        try {
            emit(Resource.Loading<AuthResult>())

            // Get the current user instance.
            val currentUser = repository.currentUser

            // Check if the currentUser instance contains a user.
            if(currentUser != null){
                // Sign out the current user.
                repository.signOut()
                sharedPreferencesRepositoryImpl.removeUser()
                emit(Resource.Success<AuthResult>())
            }else{
                emit(Resource.Error<AuthResult>("LogoutCurrentUserUseCase " + "No users are logged in"))
            }
        }catch (e: Exception){
            emit(Resource.Error<AuthResult>("LogoutCurrentUserUseCase " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}
