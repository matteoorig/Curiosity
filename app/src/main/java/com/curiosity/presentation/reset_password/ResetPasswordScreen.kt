package com.curiosity.presentation.reset_password

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
import com.curiosity.presentation.reset_password.content.ResetPasswordContent
import com.curiosity.presentation.reset_password.content.ResetPasswordError

/**
 * Composable function that represents the reset password screen of the application.
 *
 * This function is responsible for displaying the reset password UI elements.
 */
@Composable
fun ResetPasswordScreen(
    navController: NavController,
    viewModel: ResetPasswordViewModel = hiltViewModel(),
){
    val state by viewModel.state.collectAsState()
    val mailValue by viewModel.mailValue.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.sendResetPasswordEmailError != null -> {
            ResetPasswordError(
                navController = navController,
                viewModel = viewModel,
                state = state
            )
        }
        state.sendResetPasswordEmailSuccessful -> {
            LaunchedEffect(Unit){
                navController.popBackStack()
            }
        }
        else -> {
            ResetPasswordContent(
                navController = navController,
                viewModel = viewModel,
                state = state,
                mailValue = mailValue
            )
        }
    }
}

@Preview
@Composable
fun ResetPasswordScreenPreview(){
    ResetPasswordScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}