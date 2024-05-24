package com.curiosity.presentation.intro.content

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
import com.curiosity.presentation.intro.IntroStates

/**
 * Composable function that displays an error screen for the intro flow.
 *
 * This function displays an error message and a button to reload the intro screen.
 *
 * @param navController The NavController used for navigation.
 * @param state The state of the intro screen, which includes the error message.
 */
@Composable
fun IntroError(
    navController: NavController,
    state: IntroStates
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 26.dp, top = 26.dp, start = 18.dp, end = 18.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        CuriosityCoupleTitle(
            titleText = "ERROR",
            subtitleText = state.currentUserExistError ?: "Internal error"
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
    IntroError(
        navController = rememberNavController(),
        state = IntroStates()
    )
}