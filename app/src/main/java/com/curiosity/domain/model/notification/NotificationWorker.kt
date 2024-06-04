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
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curiosity.MainActivity
import com.curiosity.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Worker class for handling periodic notifications using WorkManager.
 *
 * @property context The application context used to access system services.
 * @property workerParams Parameters for the worker.
 */
@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
) : Worker(context, workerParams) {

    /**
     * The method that performs the actual work of sending a notification.
     *
     * @return Result indicating the success or failure of the work.
     */
    override fun doWork(): Result {

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
            .setContentTitle("Content Title")
            .setContentText("Content Text")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Curiosity Text"))
            .addAction(R.drawable.single_yellow_cartoon, "I know", knowCuriosityPendingIntent)
            .addAction(R.drawable.single_blue_cartoon, "I don't know", notKnowCuriosityPendingIntent)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()


        notificationManager.notify(1, notification)
        Log.i("WorkManager",
            "Notifica inviata"
        )
        return Result.success()
    }
}