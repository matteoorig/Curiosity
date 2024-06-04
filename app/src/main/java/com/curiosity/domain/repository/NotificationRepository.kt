package com.curiosity.domain.repository

/**
 * @author matteooriggi
 */

import java.util.concurrent.TimeUnit

/**
 * A repository interface for managing notification scheduling.
 */
interface NotificationRepository {

    /**
     * Schedules a notification to be executed periodically.
     *
     * @param interval The time interval between successive notifications.
     * @param timeUnit The unit of time for the interval (e.g., seconds, minutes, hours).
     */
    fun scheduleNotificationWork(interval: Long, timeUnit: TimeUnit)
}