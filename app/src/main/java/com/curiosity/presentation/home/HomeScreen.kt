package com.curiosity.presentation.home

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
import com.curiosity.presentation.home.content.HomeContent

/**
 * Composable function that represents the home screen of the application.
 *
 * This function is responsible for displaying the user home UI elements.
 */
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
){

    val state by viewModel.state.collectAsState()
    val user by viewModel.user.collectAsState()
    val curiosity by viewModel.curiosity.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.loadUserError != null -> {
            LaunchedEffect(Unit) {
                // If the user data is not present it means that onBoarding has not been done
                navController.navigate(Routes.OnBoardingScreen.route){
                    popUpTo(route = Routes.HomeScreen.route){
                        inclusive = true
                        saveState = false
                    }
                }
            }
        }
        else -> {
            HomeContent(
                navController = navController,
                viewModel = viewModel,
                state = state,
                user = user,
                curiosity = curiosity
            )
        }
    }

}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}