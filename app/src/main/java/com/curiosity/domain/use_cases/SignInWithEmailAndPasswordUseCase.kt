package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: AuthRepository,
    // prima era private val repository: AuthRepositoryImpl,
){

    operator fun invoke(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        try {
            emit(Resource.Loading<AuthResult>())
            val currentUser = repository.currentUser


            // DEV
            // se esiste già attualmente si effettua la signOut() perchè firebase ricorda l'accesso
            if(currentUser != null){
                repository.signOut()
            }


            if(currentUser != null){
                emit(Resource.Error<AuthResult>("signInUserWithEmailAndPassword " + "User already exist"))
            }else{
                val result = repository.signInUserWithEmailAndPassword(email, password)
                emit(Resource.Success<AuthResult>(data = result))
            }
        }catch (e: Exception){
            emit(Resource.Error<AuthResult>("signInUserWithEmailAndPassword " + e.message))
        }
    }.flowOn(Dispatchers.IO)
}