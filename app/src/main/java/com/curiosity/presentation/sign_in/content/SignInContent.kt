package com.curiosity.presentation.sign_in.content

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.app.routes.Routes
import com.curiosity.presentation.sign_in.SignInStates
import com.curiosity.presentation.sign_in.SignInViewModel

/**
 * Composable function that represents the content of the sign-in screen.
 *
 * This function is responsible for displaying the UI elements for the user to sign in.
 */
@Composable
fun SignInContent(
    navController: NavController,
    viewModel: SignInViewModel,
    state: SignInStates
){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "SignInContent")
        Button(
            onClick = {
                navController.navigate(Routes.IntroScreen.route)
            }
        ) {
            Text(text = "navigate to introScreenContent")
        }
    }
}

/**
 * Preview function for the SignInContent composable.
 *
 * This function is used to display a preview of the SignInContent composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun SignInContentPreview(){
    SignInContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = SignInStates()
    )
}