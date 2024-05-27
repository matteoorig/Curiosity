package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.data.model.User
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.curiosity.domain.repository.DataRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val dataRepository: DataRepository
){

    operator fun invoke(email: String, password: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())
            val currentUser = repository.currentUser

            if(currentUser != null){
                emit(Resource.Error<User>("signInUserWithEmailAndPassword " + "User already exist"))
            }else{
                val result = repository.signInUserWithEmailAndPassword(email, password)
                val userData = dataRepository.getUser(result.user!!.uid)!!.data
                val user: User = User(
                    uuid = result.user!!.uid,
                    username = userData!!["username"].toString(),
                    email = userData["email"].toString(),
                    level = userData["level"].toString().toInt(),
                    coins = userData["coins"].toString().toInt(),
                    preferences = emptyList()
                )
                emit(Resource.Success<User>(data = user))
            }
        }catch (e: Exception){
            emit(Resource.Error<User>("signInUserWithEmailAndPassword " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}