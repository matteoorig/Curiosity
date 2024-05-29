package com.curiosity.presentation.profile

/**
 * @author matteooriggi
 */

/**
 * Data class that represents the various states of the profile screen.
 */
data class ProfileStates(
    val isLoading: Boolean = false,
    val loadUserSuccess: Boolean = false,
    val loadUserError: String? = null,
    val currentUserLogout: Boolean = false,
    val currentUserLogoutError: String? = null
)
