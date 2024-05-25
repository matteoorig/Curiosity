package com.curiosity.presentation.on_boarding

/**
 * @author matteooriggi
 */

data class OnBoardingStates(
    val isLoading: Boolean = false,
    val onPresentationSuccess: Boolean = false,
    val onPresentationError: String = "",
    val onSelectAreasSuccess: Boolean = false,
    val onSelectAreasError: String = "",
    val onSelectIntervalSuccess: Boolean = false,
    val onSelectIntervalError: String = "",
    val onSummarySuccess: Boolean = false,
    val onSummaryError: String = ""
)
