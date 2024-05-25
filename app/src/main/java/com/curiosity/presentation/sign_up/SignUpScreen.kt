package com.curiosity.presentation.sign_up

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
import com.curiosity.presentation.sign_up.content.SignUpContent
import com.curiosity.presentation.sign_up.content.SignUpError

/**
 * Composable function that represents the sign-up screen of the application.
 *
 * This function is responsible for displaying the UI elements for the user to sign up.
 */
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
){
    val state by viewModel.state.collectAsState()

    val usernameValue by viewModel.usernameValue.collectAsState()
    val mailValue by viewModel.mailValue.collectAsState()
    val passwordValue by viewModel.passwordValue.collectAsState()

    when{
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.requestSignUpError != null -> {
            SignUpError(
                navController = navController,
                viewModel = viewModel,
                state = state
            )
        }
        state.requestSignUpSuccessful -> {
            LaunchedEffect(Unit){
                // TODO: mail check with otp
                // TODO: start onBoarding views
                navController.navigate(Routes.ProfileScreen.route){
                    popUpTo(route = Routes.SignUpScreen.route){
                        inclusive = true
                        saveState = false
                    }
                }
            }
        }
        else -> {
            SignUpContent(
                navController = navController,
                viewModel = viewModel,
                state = state,
                usernameValue = usernameValue,
                mailValue = mailValue,
                passwordValue = passwordValue
            )
        }
    }
}

/**
 * Preview function for the SignUpScreen composable.
 *
 * This function is used to display a preview of the SignUpScreen composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}