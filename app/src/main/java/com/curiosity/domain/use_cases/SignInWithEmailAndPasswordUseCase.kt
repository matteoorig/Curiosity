package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import android.util.Log
import com.curiosity.domain.model.Preferences
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
 * Use case for signing up with email and password.
 *
 * This use case handles the process of signing up a new user using email and password.
 * The state of the operation is managed using Flow<Resource<User>>.
 *
 * @param repository The AuthRepository used for authentication operations.
 * @param dataRepository The DataRepository used for database operations.
 * @param sharedPreferencesRepository The SharedPreferencesRepository used for saving user data locally.
 */
class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val dataRepository: DataRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
){
    /**
     * Invokes the use case to sign in with email and password.
     *
     * This method handles the entire process of signing in a user using the provided email and password,
     * retrieving the user data from Firestore, parsing the preferences, creating a User instance,
     * and saving the user data locally in SharedPreferences.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return A Flow of Resource containing the signed-in User.
     */
    operator fun invoke(email: String, password: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())
            // Get the currentUser instance
            val currentUser = repository.currentUser

            // Check if the currentUser instance contains a user.
            if(currentUser != null){
                emit(Resource.Error<User>("signInUserWithEmailAndPassword " + "User already exist"))
            }else{
                // Firebase Authentication Sign in with the credentials provided by the UI.
                // Every error is handled by the Flow.
                val result = repository.signInUserWithEmailAndPassword(email, password)

                // Get user data from Firebase Firestore
                val userData = dataRepository.getUser(result.user!!.uid)!!.data

                // Parsing preferences from Firestore data
                val preferencesList = (userData!!["preferences"] as HashMap<String, Map<String, Any>>).map { (key, valueMap) ->
                    Preferences(
                        preferenceValue = valueMap["preferenceValue"].toString(),
                        interest = (valueMap["interest"] as Number).toInt()
                    )
                }

                // Create a User instance with the obtained data.
                val user: User = User(
                    uuid = result.user!!.uid,
                    username = userData!!["username"].toString(),
                    email = userData["email"].toString(),
                    level = userData["level"].toString().toInt(),
                    coins = userData["coins"].toString().toInt(),
                    isMinutes = userData["isMinutes"] as Boolean,
                    interval = userData["interval"].toString().toInt(),
                    preferences = preferencesList
                )

                // Remove user if exist
                sharedPreferencesRepository.removeUser()

                // Save the user in shared preferences.
                sharedPreferencesRepository.saveUser(user)

                emit(Resource.Success<User>(data = user))
            }
        }catch (e: Exception){
            emit(Resource.Error<User>("signInUserWithEmailAndPassword " + e.message))
            Log.d("signInUserWithEmailAndPassword", "" + e.message)
        }
    }.flowOn(Dispatchers.IO)
}