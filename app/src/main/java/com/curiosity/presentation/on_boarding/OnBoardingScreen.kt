package com.curiosity.presentation.on_boarding

/**
 * @author matteooriggi
 */

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.on_boarding.content.OnBoardingPresentationContent

/**
 * Composable function that represents the OnBoarding screen of the application.
 *
 * This function is responsible for displaying the UI elements of the OnBoarding screen.
 */
@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel(),
){
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.onPresentationSuccess -> {

        }
        else -> {
            OnBoardingPresentationContent(
                navController = navController,
                viewModel = viewModel,
                state = state
            )
        }
    }

}

@Preview
@Composable
fun OnBoardingScreenPreview(){
    OnBoardingScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}