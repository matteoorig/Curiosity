package com.curiosity.presentation.on_boarding.content

/**
 * @author matteooriggi
 */

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.on_boarding.OnBoardingStates
import com.curiosity.presentation.on_boarding.OnBoardingViewModel

/**
 * Composable function that represents the content of the OnBoarding Select Areas screen.
 *
 * This function is responsible for displaying the UI elements that make up the OnBoarding Select Areas screen.
 */
@Composable
fun OnBoardingSelectAreasContent(
    navController: NavController,
    viewModel: OnBoardingViewModel,
    state: OnBoardingStates
){


}

@Preview
@Composable
fun OnBoardingSelectAreasContentPreview(){
    OnBoardingSelectAreasContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = OnBoardingStates()
    )
}