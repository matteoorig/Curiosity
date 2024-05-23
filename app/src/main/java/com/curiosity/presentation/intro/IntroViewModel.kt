package com.curiosity.presentation.intro

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import com.curiosity.domain.use_cases.ExistCurrentUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the intro screen.
 *
 * This class holds the state of the intro screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
class IntroViewModel @Inject constructor(
    private val existCurrentUserUseCase: ExistCurrentUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(IntroStates())
    val state: StateFlow<IntroStates> = _state.asStateFlow()

}