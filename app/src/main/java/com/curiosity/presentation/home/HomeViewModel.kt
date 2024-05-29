package com.curiosity.presentation.home

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.Resource
import com.curiosity.domain.model.User
import com.curiosity.domain.use_cases.LoadCurrentUserUseCase
import com.curiosity.presentation.on_boarding.OnBoardingStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the Home screen.
 *
 * This class holds the state of the Home screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeStates())
    val state: StateFlow<HomeStates> = _state.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    fun updateStateValue(newState: HomeStates){
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
                        _state.value = HomeStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        updateUserValue(resource.data!!)
                        _state.value = HomeStates(loadUserSuccess = true)
                    }
                    is Resource.Error -> {
                        _state.value = HomeStates(loadUserError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }
}