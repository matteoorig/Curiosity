package com.curiosity.presentation.reset_password

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.ResetUserPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the reset password screen.
 *
 * This class holds the state of the reset password screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetUserPasswordUseCase: ResetUserPasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ResetPasswordStates())
    val state: StateFlow<ResetPasswordStates> = _state

    private val _mailValue = MutableStateFlow("")
    val mailValue: StateFlow<String> = _mailValue.asStateFlow()

    fun updateStateValue(newState: ResetPasswordStates){
        _state.value = newState
    }

    fun updateMailValue(newValue: String){
        _mailValue.value = newValue
    }

    fun sendResetEmail(){
        viewModelScope.launch {
            val flow = resetUserPasswordUseCase(_mailValue.value)
            flow.onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.value = ResetPasswordStates(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = ResetPasswordStates(isLoading = false)
                    }

                    is Resource.Error -> {
                        _state.value = ResetPasswordStates(sendResetPasswordEmailError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }
}