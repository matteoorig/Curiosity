package com.curiosity.presentation.profile

/**
 * @author matteooriggi
 */

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.Resource
import com.curiosity.domain.model.User
import com.curiosity.domain.use_cases.LoadCurrentUserUseCase
import com.curiosity.domain.use_cases.LogoutCurrentUserUseCase
import com.curiosity.presentation.home.HomeStates
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
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val logoutCurrentUserUseCase: LogoutCurrentUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ProfileStates())
    val state: StateFlow<ProfileStates> = _state.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    fun updateStateValue(newState: ProfileStates){
        _state.value = newState
    }

    fun updateUserValue(newState: User){
        _user.value = newState
    }

    init {
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch {
            val flow = loadCurrentUserUseCase()
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = ProfileStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        updateUserValue(resource.data!!)
                        _state.value = ProfileStates(loadUserSuccess = true)
                    }
                    is Resource.Error -> {
                        _state.value = ProfileStates(loadUserError = resource.message)
                    }
                }
            }.launchIn(this)
        }
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