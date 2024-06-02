package com.curiosity.presentation.areas_of_interest.content

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
import com.curiosity.presentation.areas_of_interest.AreasOfInterestStates

/**
 * Composable function that displays an error screen for the areas of interest flow.
 *
 * This function displays an error message and a button to reload the areas of interest screen.
 *
 * @param navController The NavController used for navigation.
 * @param state The state of the areas of interest screen, which includes the error message.
 */
@Composable
fun AreasOfInterestError(
    navController: NavController,
    state: AreasOfInterestStates,
    error: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 26.dp, top = 26.dp, start = 18.dp, end = 18.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        CuriosityCoupleTitle(
            titleText = "ERROR",
            subtitleText = error
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
fun AreasOfInterestErrorPreview(){
    AreasOfInterestError(
        navController = rememberNavController(),
        state = AreasOfInterestStates(),
        error = "Internal Error"
    )
}