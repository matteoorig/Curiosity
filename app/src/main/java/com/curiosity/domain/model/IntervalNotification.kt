package com.curiosity.domain.model

/**
 * @author matteooriggi
 */

import java.util.concurrent.TimeUnit

data class IntervalNotification(
    val interval: Long,
    val timeUnit: TimeUnit
)
