package com.curiosity.presentation.intro

/**
 * @author matteooriggi
 */

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.intro.content.IntroContent

/**
 * Composable function that represents the intro screen of the application.
 *
 * This function is responsible for displaying the UI elements of the intro screen.
 */
@Composable
fun IntroScreen(
    navController: NavController
) {
    IntroContent(
        navController = navController
    )
}

/**
 * Preview function for the IntroScreen composable.
 *
 * This function is used to display a preview of the IntroScreen composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun IntroScreenPreview() {
    IntroScreen(
        navController = rememberNavController()
    )
}