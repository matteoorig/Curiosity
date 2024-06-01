package com.curiosity.presentation.intro

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.ExistCurrentUserUseCase
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
 * ViewModel class for managing the state of the intro screen.
 *
 * This class holds the state of the intro screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class IntroViewModel @Inject constructor(
    private val existCurrentUserUseCase: ExistCurrentUserUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(IntroStates())
    val state: StateFlow<IntroStates> = _state.asStateFlow()

    init {
        checkUserExist()
    }

    private fun checkUserExist() {
        viewModelScope.launch {
            existCurrentUserUseCase().onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.value = IntroStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = IntroStates(
                            isLoading = false,
                            currentUserExist = resource.data ?: false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = IntroStates(
                            isLoading = false,
                            currentUserExistError = resource.message
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}