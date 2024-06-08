package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.repository.NotificationRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Use case for scheduling notification work.
 *
 * This use case handles the process of scheduling a notification worker to run at specified intervals.
 * It interacts with the NotificationRepository to set up the work manager for notifications.
 *
 * @property notificationRepository The NotificationRepository used for scheduling notification work.
 */
class NotificationWorkerUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    /**
     * Executes the use case to schedule the notification worker.
     *
     * This method schedules the notification worker to run at the specified interval.
     */
    fun execute(){
        notificationRepository.scheduleNotificationWork(15, TimeUnit.MINUTES)
    }
}