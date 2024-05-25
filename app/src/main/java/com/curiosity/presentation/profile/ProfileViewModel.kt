package com.curiosity.presentation.profile

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.LogoutCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the profile screen.
 *
 * This class holds the state of the profile screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutCurrentUserUseCase: LogoutCurrentUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ProfileStates())
    val state: StateFlow<ProfileStates> = _state.asStateFlow()

    fun updateStateValue(newState: ProfileStates){
        _state.value = newState
    }

    fun logOut(){
        viewModelScope.launch {
            val flow = logoutCurrentUserUseCase()
            flow.onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.value = ProfileStates(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = ProfileStates(isLoading = false, currentUserLogout = true)
                    }

                    is Resource.Error -> {
                        _state.value = ProfileStates(isLoading = false, currentUserLogoutError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }
}