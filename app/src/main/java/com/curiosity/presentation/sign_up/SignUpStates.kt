package com.curiosity.presentation.sign_up

/**
 * @author matteooriggi
 */

/**
 * Data class that represents the various states of the sign-up screen.
 */
data class SignUpStates(
    val isLoading: Boolean = false,
    val requestSignUpSuccessful: Boolean = false,
    val requestSignUpError: String? = null
)
