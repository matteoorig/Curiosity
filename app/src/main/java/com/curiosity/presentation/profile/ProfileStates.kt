package com.curiosity.presentation.profile

/**
 * @author matteooriggi
 */

/**
 * Data class that represents the various states of the profile screen.
 */
data class ProfileStates(
    val isLoading: Boolean = false,
    val currentUserLogout: Boolean = false,
    val currentUserLogoutError: String? = null
)
