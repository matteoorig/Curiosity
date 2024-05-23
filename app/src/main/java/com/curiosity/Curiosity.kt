package com.curiosity

/**
 * @author matteooriggi
 */

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the Curiosity app.
 *
 * This class is annotated with @HiltAndroidApp to trigger Hilt's code generation and
 * to automatically integrate Hilt into the Android application lifecycle.
 */
@HiltAndroidApp
class Curiosity : Application()