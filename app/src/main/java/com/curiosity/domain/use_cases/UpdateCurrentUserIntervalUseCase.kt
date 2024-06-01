package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Preferences
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import com.curiosity.domain.repository.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateCurrentUserIntervalUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataRepository: DataRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
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