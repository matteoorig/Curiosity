package com.curiosity.presentation.sign_in

/**
 * @author matteooriggi
 */
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.sign_in.content.SignInContent

/**
 * Composable function that represents the sign-in screen of the application.
 *
 * This function is responsible for displaying the UI elements for the user to sign in.
 */
@Composable
fun SignInScreen(
    navController: NavController
){
    SignInContent(
        navController = navController
    )
}

/**
 * Preview function for the SignInScreen composable.
 *
 * This function is used to display a preview of the SignInScreen composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun SignInScreenPreview(){
    SignInScreen(
        navController = rememberNavController()
    )
}