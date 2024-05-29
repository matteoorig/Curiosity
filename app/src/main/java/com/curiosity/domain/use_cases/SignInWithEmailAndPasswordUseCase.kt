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

                // Create a User instance with the obtained data.
                val user: User = User(
                    uuid = result.user!!.uid,
                    username = userData!!["username"].toString(),
                    email = userData["email"].toString(),
                    level = userData["level"].toString().toInt(),
                    coins = userData["coins"].toString().toInt(),
                )

                // Remove user if exist
                sharedPreferencesRepository.removeUser()

                // Save the user in shared preferences.
                sharedPreferencesRepository.saveUser(user)

                emit(Resource.Success<User>(data = user))
            }
        }catch (e: Exception){
            emit(Resource.Error<User>("signInUserWithEmailAndPassword " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}