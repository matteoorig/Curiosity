package com.curiosity.presentation.on_boarding.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.R
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.common.components.CuriosityIntervalNotification
import com.curiosity.common.components.CuriositySvg
import com.curiosity.common.components.CuriosityText
import com.curiosity.presentation.on_boarding.OnBoardingStates
import com.curiosity.presentation.on_boarding.OnBoardingViewModel
import com.curiosity.ui.theme.CuriosityBlue
import com.curiosity.ui.theme.CuriosityViolet

/**
 * Composable function that represents the content of the OnBoarding Select Interval screen.
 *
 * This function is responsible for displaying the UI elements that make up the OnBoarding Select Interval screen.
 */
@Composable
fun OnBoardingSelectIntervalContent(
    navController: NavController,
    viewModel: OnBoardingViewModel,
    state: OnBoardingStates,
    isMinutes: Boolean,
    interval: Int
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
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                CuriosityText(
                    modifier = Modifier,
                    value = "Enter the time interval",
                    textSize = 24.sp,
                    textWeight = FontWeight.Medium,
                    textColor = CuriosityViolet,
                )
                CuriosityText(
                    modifier = Modifier,
                    value = "between notifications",
                    textSize = 24.sp,
                    textWeight = FontWeight.Medium,
                    textColor = CuriosityViolet
                )
            }
            CuriositySvg(
                drawableResource = R.drawable.single_blue_cartoon
            )
        }
        CuriosityIntervalNotification(
            isMinutes = isMinutes,
            interval = interval,
            updateIsMinutes = {
                viewModel.updateIsMinutesValue(it)
            },
            updateInterval = {
                viewModel.updateIntervalValue(it)
            }
        )

        CuriosityDefaultButton(
            value = "NEXT",
            valueColor = Color.White,
            backgroundColor = CuriosityBlue,
            onClick = {
                viewModel.confirmSelectionIntervalNotification()
            }
        )
    }

}

@Preview
@Composable
fun OnBoardingSelectIntervalContentPreview(){
    OnBoardingSelectIntervalContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = OnBoardingStates(),
        isMinutes = false,
        interval = 1
    )
}