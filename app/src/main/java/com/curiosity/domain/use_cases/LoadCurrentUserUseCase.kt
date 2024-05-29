package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Resource
import com.curiosity.domain.model.User
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import com.curiosity.domain.repository.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for loading the current user.
 *
 * This use case handles the process of loading the currently authenticated user's data.
 * It interacts with the AuthRepository to get the current user and the SharedPreferencesRepository
 * to fetch the user data stored locally. The state of the operation is managed using Flow<Resource<User>>.
 *
 * @param authRepository The AuthRepository used for authentication operations.
 * @param sharedPreferencesRepository The SharedPreferencesRepository used for fetching user data from local storage.
 */
class LoadCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
    operator fun invoke(): Flow<Resource<User>> = flow {
        try {
            // Get the current user instance.
            val currentUser = authRepository.currentUser

            // Check if the currentUser instance contains a user.
            if(currentUser != null){
                // Load the data relating to the user present in sharedPreferences
                val user: User? = sharedPreferencesRepository.getUser()

                // If exist user
                if(user != null && user.preferences.isNotEmpty()){
                    emit(Resource.Success<User>(data = user))
                }else{
                    emit(Resource.Error<User>("LoadCurrentUserUseCase " + "No user data present"))
                }

            }else{
                emit(Resource.Error<User>("LoadCurrentUserUseCase " + "No users are logged in"))
            }

        }catch (e: Exception){
            emit(Resource.Error<User>("LoadCurrentUserUseCase " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}