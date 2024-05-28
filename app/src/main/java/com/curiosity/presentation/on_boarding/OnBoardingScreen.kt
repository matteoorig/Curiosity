package com.curiosity.presentation.on_boarding

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
import com.curiosity.presentation.on_boarding.content.OnBoardingError
import com.curiosity.presentation.on_boarding.content.OnBoardingPresentationContent
import com.curiosity.presentation.on_boarding.content.OnBoardingSelectAreasContent
import com.curiosity.presentation.on_boarding.content.OnBoardingSelectIntervalContent
import com.curiosity.presentation.on_boarding.content.OnBoardingSummaryContent

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
    val isMinutes by viewModel.isMinutes.collectAsState()
    val interval by viewModel.interval.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.loadAreasOfInterestError != null -> {
            OnBoardingError(
                navController = navController,
                state = state,
                error = state.loadAreasOfInterestError!!
            )
        }
        state.onPresentationSuccess -> {
            OnBoardingSelectAreasContent(
                navController = navController,
                viewModel = viewModel,
                state = state
            )
        }
        state.onSelectAreasError != null -> {
            OnBoardingError(
                navController = navController,
                state = state,
                error = state.onSelectAreasError!!
            )
        }
        state.onSelectAreasSuccess -> {
            OnBoardingSelectIntervalContent(
                navController = navController,
                viewModel = viewModel,
                state = state,
                isMinutes = isMinutes,
                interval = interval
            )
        }
        state.onSelectIntervalError != null -> {
            OnBoardingError(
                navController = navController,
                state = state,
                error = state.onSelectIntervalError!!
            )
        }
        state.onSelectIntervalSuccess -> {
            OnBoardingSummaryContent(
                navController = navController,
                viewModel = viewModel,
                state = state
            )
        }
        state.onSummarySuccess -> {
            LaunchedEffect(Unit){
                navController.navigate(Routes.HomeScreen.route){
                    popUpTo(route = Routes.OnBoardingScreen.route){
                        inclusive = true
                        saveState = false
                    }
                }
            }
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