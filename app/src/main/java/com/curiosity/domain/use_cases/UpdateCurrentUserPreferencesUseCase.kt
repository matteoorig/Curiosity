package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Preferences
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for updating the current user's preferences.
 *
 * This use case handles the process of updating the preferences for the currently
 * authenticated user. It interacts with the AuthRepository to get the current user
 * and the DataRepository to update the user's preferences in the database. The state
 * of the operation is managed using Flow<Resource<Boolean>>.
 *
 * @param repository The AuthRepository used for authentication operations.
 * @param dataRepository The DataRepository used for database operations.
 */
class UpdateCurrentUserPreferencesUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val dataRepository: DataRepository
) {
    operator fun invoke(preferences: List<Preferences>) : Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            // Get the current user instance.
            val currentUser = repository.currentUser

            // Check if the currentUser instance contains a user.
            if(currentUser == null){
                emit(Resource.Error<Boolean>("InsertCurrentUserPreferencesUseCase no user logged in"))
            }else{
                // Update the preferences of the current user in Firebase Firestore
                // Every error is handled by the Flow.
                dataRepository.updateUserPreferences(currentUser.uid, preferences)

                emit(Resource.Success<Boolean>(data = true))
            }

        }catch (e: Exception){
            emit(Resource.Error<Boolean>("InsertCurrentUserPreferencesUseCase " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}