package com.curiosity.presentation.interval_notification

/**
 * @author matteooriggi
 */

data class IntervalNotificationStates(
    val isLoading: Boolean = false,
    val loadCurrentUserIntervalNotificationSuccess: Boolean = false,
    val loadCurrentUserIntervalNotificationError: String? = null,
    val updateCurrentUserIntervalNotificationSuccess: Boolean = false,
    val updateCurrentUserIntervalNotificationError: String? = null
)
