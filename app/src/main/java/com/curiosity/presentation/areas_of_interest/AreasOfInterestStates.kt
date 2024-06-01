package com.curiosity.presentation.areas_of_interest

/**
 * @author matteooriggi
 */

data class AreasOfInterestStates(
    val isLoading: Boolean = false,
    val loadAreasOfInterestSuccess: Boolean = false,
    val loadAreasOfInterestError: String? = null,
    val loadCurrentUserAreasOfInterestSuccess: Boolean = false,
    val loadCurrentUserAreasOfInterestError: String? = null,
    val updateCurrentUserAreasOfInterestSuccess: Boolean = false,
    val updateCurrentUserAreasOfInterestError: String? = null
)
