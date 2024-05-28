package com.curiosity.presentation.sign_up

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.SignUpWithEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the sign-up screen.
 *
 * This class holds the state of the sign-up screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SignUpStates())
    val state: StateFlow<SignUpStates> = _state.asStateFlow()

    private val _usernameValue = MutableStateFlow("")
    val usernameValue: StateFlow<String> = _usernameValue.asStateFlow()

    private val _mailValue = MutableStateFlow("")
    val mailValue: StateFlow<String> = _mailValue.asStateFlow()

    private val _passwordValue = MutableStateFlow("")
    val passwordValue: StateFlow<String> = _passwordValue.asStateFlow()

    fun updateUsernameValue(newValue: String){
        _usernameValue.value = newValue
    }

    fun updateMailValue(newValue: String){
        _mailValue.value = newValue
    }

    fun updatePasswordValue(newValue: String){
        _passwordValue.value = newValue
    }

    fun updateStateValue(newState: SignUpStates){
        _state.value = newState
    }

    fun signUpUser(){
        viewModelScope.launch {
            val flow = signUpWithEmailAndPasswordUseCase(_usernameValue.value, _mailValue.value, _passwordValue.value)
            flow.onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.value = SignUpStates(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = SignUpStates(isLoading = false,requestSignUpSuccessful = true)
                    }

                    is Resource.Error -> {
                        _state.value = SignUpStates(isLoading = false, requestSignUpError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }
}