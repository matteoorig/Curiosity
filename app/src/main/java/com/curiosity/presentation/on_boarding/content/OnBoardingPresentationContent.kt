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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.R
import com.curiosity.common.components.CuriosityCoupleTitle
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.common.components.CuriositySvg
import com.curiosity.presentation.on_boarding.OnBoardingStates
import com.curiosity.presentation.on_boarding.OnBoardingViewModel
import com.curiosity.ui.theme.CuriosityViolet

/**
 * Composable function that represents the content of the OnBoarding Presentation screen.
 *
 * This function is responsible for displaying the UI elements that make up the OnBoarding Presentation screen.
 */
@Composable
fun OnBoardingPresentationContent(
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
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CuriosityCoupleTitle(
            titleText = "Hi there!",
            subtitleText = "Before you start, we have\n" +
                    "just a few questions"
        )
        CuriositySvg(
            drawableResource = R.drawable.cartoon_group
        )
        CuriosityDefaultButton(
            value = "BEGIN",
            valueColor = Color.White,
            backgroundColor = CuriosityViolet,
            onClick = {
                viewModel.updateStateValue(OnBoardingStates(
                    onPresentationSuccess = true
                ))
            }
        )
    }

}

@Preview
@Composable
fun OnBoardingPresentationContentPreview(){
    OnBoardingPresentationContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = OnBoardingStates()
    )
}