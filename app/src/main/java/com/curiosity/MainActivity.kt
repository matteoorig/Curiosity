package com.curiosity

/**
 * @author matteooriggi
 */

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.curiosity.presentation.app.navigation.CuriosityNavigationGraph

/**
 * MainActivity class that serves as the entry point of the application.
 *
 * This activity is responsible for setting up the main content view of the application,
 * which includes the navigation graph.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     *                           then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "curiosity_identifier",
                "curiosity push notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Curiosity application notification transmission channel"
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        setContent {
            CuriosityNavigationGraph()
        }
    }
}
