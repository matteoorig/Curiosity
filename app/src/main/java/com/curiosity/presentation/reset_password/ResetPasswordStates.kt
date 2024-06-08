package com.curiosity.presentation.reset_password

data class ResetPasswordStates(
    val isLoading: Boolean = false,
    val sendResetPasswordEmailSuccessful: Boolean = false,
    val sendResetPasswordEmailError: String? = null
)
