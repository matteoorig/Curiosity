package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import com.curiosity.domain.repository.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for updating the current user's notification interval.
 *
 * This use case handles the process of updating the notification interval and its type (minutes or hours) for the currently authenticated user.
 * It interacts with the AuthRepository to get the current user, the DataRepository to update the user data in Firestore,
 * and the SharedPreferencesRepository to update the user data locally. The state of the operation is managed using Flow<Resource<Boolean>>.
 *
 * @property authRepository The AuthRepository used for authentication operations.
 * @property dataRepository The DataRepository used for database operations.
 * @property sharedPreferencesRepository The SharedPreferencesRepository used for saving user data locally.
 */
class UpdateCurrentUserIntervalUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataRepository: DataRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
    /**
     * Invokes the use case to update the current user's notification interval.
     *
     * This method updates the notification interval and its type (minutes or hours) for the currently authenticated user,
     * both in Firestore and locally in SharedPreferences. The state of the operation is emitted as a Flow of Resource containing a Boolean value.
     *
     * @param isMinutes Boolean indicating whether the interval is in minutes.
     * @param interval The length of the interval.
     * @return A Flow of Resource containing a Boolean value indicating the success or failure of the operation.
     */
    operator fun invoke(isMinutes: Boolean, interval: Int) : Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            // Get the current user instance.
            val currentUser = authRepository.currentUser

            // Check if the currentUser instance contains a user.
            if(currentUser == null){
                emit(Resource.Error<Boolean>("UpdateCurrentUserIntervalUseCase no user logged in"))
            }else{
                // Update the time interval and its type of the current user in Firebase Firestore
                // Every error is handled by the Flow.
                dataRepository.updateUserInterval(currentUser.uid, isMinutes, interval)

                // Update user time interval and its type in the sharedPreferences.
                sharedPreferencesRepository.saveCurrentUserInterval(isMinutes, interval)

                emit(Resource.Success<Boolean>(data = true))
            }

        }catch (e: Exception){
            emit(Resource.Error<Boolean>("UpdateCurrentUserIntervalUseCase " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}