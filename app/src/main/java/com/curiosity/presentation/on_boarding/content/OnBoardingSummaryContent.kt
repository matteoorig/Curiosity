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
 * Composable function that represents the content of the OnBoarding Recap screen.
 *
 * This function is responsible for displaying the UI elements that make up the OnBoarding Recap screen.
 */
@Composable
fun OnBoardingSummaryContent(
    navController: NavController,
    viewModel: OnBoardingViewModel,
    state: OnBoardingStates
){


}

@Preview
@Composable
fun OnBoardingSummaryContentPreview(){
    OnBoardingSummaryContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = OnBoardingStates()
    )
}