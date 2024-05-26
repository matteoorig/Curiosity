package com.curiosity.presentation.on_boarding.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 26.dp,
                top = 26.dp,
                start = 18.dp,
                end = 18.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }

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