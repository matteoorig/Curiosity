package com.curiosity.domain.use_cases

/**
 * @author matteooriggi
 */

import com.curiosity.domain.repository.NotificationRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NotificationWorkerUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    fun execute(){
        notificationRepository.scheduleNotificationWork(15, TimeUnit.MINUTES)
    }
}