package com.curiosity.presentation.sign_in

/**
 * @author matteooriggi
 */
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.app.routes.Routes
import com.curiosity.presentation.sign_in.content.SignInContent
import com.curiosity.presentation.sign_in.content.SignInError

/**
 * Composable function that represents the sign-in screen of the application.
 *
 * This function is responsible for displaying the UI elements for the user to sign in.
 */
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel(),
){
    val state by viewModel.state.collectAsState()
    val mailValue by viewModel.mailValue.collectAsState()
    val passwordValue by viewModel.passwordValue.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.requestSignInError != null -> {
            SignInError(
                navController = navController,
                viewModel = viewModel,
                state = state
            )
        }
        state.requestSignInSuccessful -> {
            LaunchedEffect(Unit){
                // navController.clearBackStack(Routes.SignInScreen.route)
                // navController.navigate(Routes.ProfileScreen.route)
            }
        }
        else -> {
            SignInContent(
                navController = navController,
                viewModel = viewModel,
                state = state,
                mailValue = mailValue,
                passwordValue = passwordValue
            )
        }
    }


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