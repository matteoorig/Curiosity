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
 * Composable function that represents the content of the OnBoarding Select Interval screen.
 *
 * This function is responsible for displaying the UI elements that make up the OnBoarding Select Interval screen.
 */
@Composable
fun OnBoardingSelectIntervalContent(
    navController: NavController,
    viewModel: OnBoardingViewModel,
    state: OnBoardingStates
){


}

@Preview
@Composable
fun OnBoardingSelectIntervalContentPreview(){
    OnBoardingSelectIntervalContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = OnBoardingStates()
    )
}