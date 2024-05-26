package com.curiosity.presentation.on_boarding

/**
 * @author matteooriggi
 */

data class OnBoardingStates(
    val isLoading: Boolean = false,
    val loadAreasOfInterestSuccess: Boolean = false,
    val loadAreasOfInterestError: String? = null,
    val onPresentationSuccess: Boolean = false,
    val onPresentationError: String? = null,
    val onSelectAreasSuccess: Boolean = false,
    val onSelectAreasError: String? = null,
    val onSelectIntervalSuccess: Boolean = false,
    val onSelectIntervalError: String? = null,
    val onSummarySuccess: Boolean = false,
    val onSummaryError: String? = null,
)
