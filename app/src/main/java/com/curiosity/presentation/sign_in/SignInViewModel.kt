package com.curiosity.presentation.sign_in

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.SignInWithEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the sign-in screen.
 *
 * This class holds the state of the sign-in screen using a StateFlow and provides
 * a way to observe changes to that state.
 */

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SignInStates())
    val state: StateFlow<SignInStates> = _state

    private val _mailValue = MutableStateFlow("")
    val mailValue: StateFlow<String> = _mailValue.asStateFlow()

    private val  _passwordValue = MutableStateFlow("")
    val passwordValue: StateFlow<String> = _passwordValue.asStateFlow()

    fun updateMailValue(newValue: String) {
        _mailValue.value = newValue
    }

    fun updatePasswordValue(newValue: String) {
        _passwordValue.value = newValue
    }

    fun updateStateValue(newState: SignInStates){
        _state.value = newState
    }

    fun signInUser() {
        viewModelScope.launch {
            signInWithEmailAndPasswordUseCase(_mailValue.value, _passwordValue.value).onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = SignInStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = SignInStates(isLoading = false, requestSignInSuccessful = true)
                    }
                    is Resource.Error -> {
                        _state.value = SignInStates(isLoading = false, requestSignInError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }
}