package com.curiosity.presentation.areas_of_interest

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
import com.curiosity.presentation.areas_of_interest.content.AreasOfInterestContent
import com.curiosity.presentation.areas_of_interest.content.AreasOfInterestError

@Composable
fun AreasOfInterestScreen(
    navController: NavController,
    viewModel: AreasOfInterestViewModel = hiltViewModel(),
){
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.loadAreasOfInterestError != null -> {
            AreasOfInterestError(
                navController = navController,
                state = state
            )
        }
        state.updateCurrentUserAreasOfInterestSuccess -> {
            LaunchedEffect(Unit){
                navController.popBackStack()
            }
        }
        else -> {
            AreasOfInterestContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Preview
@Composable
fun AreasOfInterestScreenPreview(){
    AreasOfInterestScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}