package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Preferences
import com.curiosity.domain.model.User
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for signing up with email and password.
 *
 * This use case handles the process of signing up a new user using email and password.
 * It interacts with the AuthRepository to perform the sign-up operation and the DataRepository
 * to register the user in the database. The state of the operation is managed using Flow<Resource<AuthResult>>.
 *
 * @param repository The AuthRepository used for authentication operations.
 * @param dataRepository The DataRepository used for database operations.
 */
class SignUpWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val dataRepository: DataRepository
) {
    operator fun invoke(username: String, email: String, password: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())
            val currentUser = repository.currentUser


            if(currentUser != null){
                emit(Resource.Error<User>("createUserWithEmailAndPassword " + "User already exist"))
            }else{
                val result = repository.createUserWithEmailAndPassword(email, password)
                val preferencesHashMap: HashMap<String, Map<String, Any>> = HashMap<String, Map<String, Any>>()

                val user: User = User(
                    uuid = result.user!!.uid,
                    username = username,
                    email = email,
                    password = password,
                    preferences = Preferences.fromHashMap(preferencesHashMap)
                )

                dataRepository.registerUser(user)
                emit(Resource.Success<User>(data = user))
            }
        }catch (e: Exception){
            emit(Resource.Error<User>("createUserWithEmailAndPassword" + e.message))
        }
    }.flowOn(Dispatchers.IO)
}