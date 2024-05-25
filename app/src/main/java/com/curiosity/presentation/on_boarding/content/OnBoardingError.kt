package com.curiosity.presentation.on_boarding.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.common.components.CuriosityCoupleTitle
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.presentation.on_boarding.OnBoardingStates

/**
 * Composable function that displays an error screen for the OnBoarding flow.
 *
 * This function displays an error message and a button to reload the OnBoarding screen.
 *
 * @param navController The NavController used for navigation.
 * @param state The state of the OnBoarding screen, which includes the error message.
 */
@Composable
fun OnBoardingError(
    navController: NavController,
    state: OnBoardingStates
){
    var errorMessage: String
    if(state.onPresentationError.isNotEmpty()){
        errorMessage = state.onPresentationError
    }else if(state.onSelectAreasError.isNotEmpty()){
        errorMessage = state.onSelectAreasError
    }else{
        errorMessage = state.onSelectIntervalError
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 26.dp, top = 26.dp, start = 18.dp, end = 18.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        CuriosityCoupleTitle(
            titleText = "ERROR",
            subtitleText = errorMessage
        )
        CuriosityDefaultButton(
            value = "Reload",
            onClick = {
                navController.popBackStack()
            }
        )
    }
}

@Preview
@Composable
fun IntroErrorPreview(){
    OnBoardingError(
        navController = rememberNavController(),
        state = OnBoardingStates()
    )
}