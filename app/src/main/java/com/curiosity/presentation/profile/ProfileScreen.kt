package com.curiosity.presentation.profile

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
import com.curiosity.presentation.profile.content.ProfileError

/**
 * Composable function that represents the profile screen of the application.
 *
 * This function is responsible for displaying the user profile UI elements.
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
){

    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.currentUserLogout -> {
            LaunchedEffect(Unit){
                // TODO: bisogna mostrare intro screen
            }
        }
        state.currentUserLogoutError != null -> {
            ProfileError(
                navController = navController,
                viewModel = viewModel,
                state = state
            )
        }
        else -> {

        }
    }
}

/**
 * Preview function for the ProfileScreen composable.
 *
 * This function is used to display a preview of the ProfileScreen composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}