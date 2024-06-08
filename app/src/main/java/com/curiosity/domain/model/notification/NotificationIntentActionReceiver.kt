package com.curiosity.domain.model.notification

/**
 * @author matteooriggi
 */

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver

/**
 * A BroadcastReceiver for handling notification action intents.
 */
@SuppressLint("RestrictedApi")
class NotificationIntentActionReceiver : BroadcastReceiver() {

    /**
     * Handles the received broadcast intents for notification actions.
     *
     * @param context The context in which the receiver is running.
     * @param intent The intent being received.
     */
    override fun onReceive(context: Context, intent: Intent?) {
        when (intent?.action) {
            KNOW_CURIOSITY -> {
                Toast.makeText(context, "Know the curiosity action received", Toast.LENGTH_SHORT).show()
            }
            NOT_KNOW_CURIOSITY -> {
                Toast.makeText(context, "Don't know the curiosity action received", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Companion object to hold the constants for the actions.
     */
    companion object {
        const val KNOW_CURIOSITY = "com.curiosity.KNOW_CURIOSITY"
        const val NOT_KNOW_CURIOSITY = "com.curiosity.NOT_KNOW_CURIOSITY"
    }
}