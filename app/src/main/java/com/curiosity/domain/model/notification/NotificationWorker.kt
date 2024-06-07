package com.curiosity.domain.model.notification

/**
 * @author matteooriggi
 */

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.curiosity.MainActivity
import com.curiosity.R
import com.curiosity.domain.model.CuriosityData
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.GetCuriosityUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Worker class for handling periodic notifications using WorkManager.
 *
 * This worker fetches curiosity data using a use case and sends notifications based on the retrieved data.
 *
 * @property context The application context used to access system services.
 * @property workerParams Parameters for the worker.
 * @property getCuriosityUseCase The use case to fetch curiosity data.
 */

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getCuriosityUseCase: GetCuriosityUseCase
) : CoroutineWorker(context, workerParams) {

    /**
     * Manage the work of fetching data and sending notifications.
     *
     * @return Result indicating the success or failure of the work.
     */
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                getCuriosityUseCase().collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            Log.i("WorkManager", "Loading curiosity data...")
                        }
                        is Resource.Success -> {
                            resource.data?.let {
                                sendNotification(it)
                            } ?: run {
                                sendNotification(CuriosityData(value = "Internal Error"))
                            }
                        }
                        is Resource.Error -> {
                            sendNotification(CuriosityData(value = "Internal Error"))
                        }
                    }
                }
                Result.success()
            } catch (e: Exception) {
                Log.e("WorkManager", "Error executing work", e)
                Result.failure()
            }
        }
    }

    /**
     * Sends a notification with the provided curiosity data.
     *
     * @param data The curiosity data to be displayed in the notification.
     */
    private fun sendNotification(data: CuriosityData) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val knowCuriosityIntent = Intent(applicationContext, NotificationIntentActionReceiver::class.java).apply {
            action = NotificationIntentActionReceiver.KNOW_CURIOSITY
        }
        val knowCuriosityPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            1,
            knowCuriosityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notKnowCuriosityIntent = Intent(applicationContext, NotificationIntentActionReceiver::class.java).apply {
            action = NotificationIntentActionReceiver.NOT_KNOW_CURIOSITY
        }
        val notKnowCuriosityPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            2,
            notKnowCuriosityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(applicationContext, "curiosity_identifier")
            .setSmallIcon(R.drawable.cartoon_group)
            .setContentTitle("New Curiosity")
            .setContentText(data.value)
            .setStyle(NotificationCompat.BigTextStyle().bigText(data.value))
            .addAction(R.drawable.single_orange_cartoon, "I know", knowCuriosityPendingIntent)
            .addAction(R.drawable.single_blue_cartoon, "I don't know", notKnowCuriosityPendingIntent)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)

        Log.i("WorkManager", "Notifica inviata")
    }
}