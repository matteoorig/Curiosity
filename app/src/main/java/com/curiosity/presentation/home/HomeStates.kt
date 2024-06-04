package com.curiosity.presentation.home

/**
 * @author matteooriggi
 */

data class HomeStates(
    val isLoading: Boolean = false,
    val loadUserSuccess: Boolean = false,
    val loadUserError: String? = null,
    val curiosityIsLoading: Boolean = false,
    val loadCurrentCuriositySuccess: Boolean = false,
    val loadCurrentCuriosityError: String? = null
)
