package com.curiosity.presentation.sign_in

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel class for managing the state of the sign-in screen.
 *
 * This class holds the state of the sign-in screen using a StateFlow and provides
 * a way to observe changes to that state.
 */

class SignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInStates())
    val state: StateFlow<SignInStates> = _state

}