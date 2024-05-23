package com.curiosity.presentation.intro.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.app.routes.Routes

/**
 * Composable function that represents the content of the intro screen.
 *
 * This function is responsible for displaying the UI elements that make up the intro screen.
 */
@Composable
fun IntroContent(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "introScreenContent")
        Button(
            onClick = {
                navController.navigate(Routes.SignInScreen.route)
            }
        ) {
            Text(text = "navigate to signInScreen")
        }
    }
}

/**
 * Preview function for the IntroContent composable.
 *
 * This function is used to display a preview of the IntroContent composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun IntroScreenContentPreview() {
    IntroContent(
        navController = rememberNavController()
    )
}