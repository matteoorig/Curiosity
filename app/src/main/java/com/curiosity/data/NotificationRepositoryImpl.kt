package com.curiosity.data

/**
 * @author matteooriggi
 */

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.curiosity.domain.model.notification.NotificationWorker
import com.curiosity.domain.model.IntervalNotification
import com.curiosity.domain.repository.NotificationRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Implementation of the NotificationRepository interface to manage scheduling notifications.
 *
 * @property context The application context used to access system services.
 * @property sharedPreferencesRepositoryImpl An instance of SharedPreferencesRepositoryImpl to access shared preferences.
 */
class NotificationRepositoryImpl @Inject constructor(
    private val context: Context,
    private val sharedPreferencesRepositoryImpl: SharedPreferencesRepositoryImpl
): NotificationRepository {

    /**
     * Schedules a notification work to be executed periodically based on user preferences or default values.
     *
     * @param interval The default interval at which the notification work should be executed.
     * @param timeUnit The time unit for the interval.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun scheduleNotificationWork(interval: Long, timeUnit: TimeUnit) {

        val userInterval: IntervalNotification? = sharedPreferencesRepositoryImpl.getInterval()

        // PeriodicWorkRequestBuilder<> as default minimum interval value to 15 minutes
        val notificationWorker = PeriodicWorkRequestBuilder<NotificationWorker>(
            repeatInterval = userInterval?.interval ?: interval,
            repeatIntervalTimeUnit = userInterval?.timeUnit ?: timeUnit
        )
            .build()

        Log.i("WorkManager",
            "La notifica ha intervallo di: " + if(userInterval?.interval != null) "" + userInterval.interval + " più timeUnit: " + userInterval.timeUnit  else "default interval di $interval"
        )

        val workManager = WorkManager.getInstance(context)

        workManager.enqueueUniquePeriodicWork(
            "curiosity_work",
            ExistingPeriodicWorkPolicy.KEEP,
            notificationWorker
        )
    }

}