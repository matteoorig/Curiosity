package com.curiosity

/**
 * @author matteooriggi
 */

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.curiosity.domain.model.notification.NotificationWorker
import com.curiosity.domain.use_cases.GetCuriosityUseCase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Application class for the Curiosity app.
 *
 * This class is annotated with @HiltAndroidApp to trigger Hilt's code generation and
 * to automatically integrate Hilt into the Android application lifecycle.
 */
@HiltAndroidApp
class Curiosity : Application(), Configuration.Provider {

    /**
     * A custom WorkerFactory that is used to create instances of Workers with dependency injection.
     */
    @Inject
    lateinit var workerFactory: CustomWorkerFactory

    /**
     * Provides the WorkManager configuration which includes the custom WorkerFactory and
     * sets the minimum logging level.
     *
     * @return WorkManager configuration.
     */
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
}

/**
 * CustomWorkerFactory is a custom implementation of WorkerFactory to create
 * instances of NotificationWorker with dependency injection.
 *
 * @property getCuriosityUseCase An instance of GetCuriosityUseCase to be injected into Workers.
 */
class CustomWorkerFactory @Inject constructor(
    private val getCuriosityUseCase: GetCuriosityUseCase
): WorkerFactory(){
    /**
     * Creates an instance of a ListenableWorker based on the worker class name.
     *
     * @param appContext The application context.
     * @param workerClassName The name of the worker class to be instantiated.
     * @param workerParameters Parameters required for worker initialization.
     * @return An instance of NotificationWorker if the class name matches, otherwise null.
     */
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? = NotificationWorker(
        appContext,
        workerParameters,
        getCuriosityUseCase
    )

}