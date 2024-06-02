package com.curiosity.presentation.intro

/**
 * @author matteooriggi
 */

/**
 * Data class that represents the various states of the intro screen.
 */
data class IntroStates(
    val isLoading: Boolean = false,
    val hasNotificationPermissionSuccessful: Boolean = false,
    val hasNotificationPermissionError: String? = null,
    val currentUserExist: Boolean = false,
    val currentUserExistError: String? = null
)
