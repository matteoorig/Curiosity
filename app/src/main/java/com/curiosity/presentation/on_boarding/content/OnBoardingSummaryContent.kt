package com.curiosity.presentation.on_boarding.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.R
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.common.components.CuriositySvg
import com.curiosity.common.components.CuriosityText
import com.curiosity.presentation.on_boarding.OnBoardingStates
import com.curiosity.presentation.on_boarding.OnBoardingViewModel
import com.curiosity.ui.theme.CuriosityBlue
import com.curiosity.ui.theme.CuriosityViolet

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
                top = 100.dp,
                start = 18.dp,
                end = 18.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CuriosityText(
            value = "So, to recap",
            textColor = CuriosityViolet,
            textSize = 24.sp,
            textWeight = FontWeight.Bold
        )

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CuriositySvg(
                    drawableResource = R.drawable.recap_areas_of_interest
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    CuriosityText(
                        modifier = Modifier,
                        value = "Your areas of interest",
                        textColor = CuriosityViolet,
                        textSize = 16.sp,
                        textWeight = FontWeight.SemiBold
                    )
                    CuriosityText(
                        modifier = Modifier,
                        value = viewModel.getAreasOfInterestValuesFormatted(),
                        textColor = CuriosityViolet,
                        textSize = 16.sp,
                        textWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CuriositySvg(
                    drawableResource = R.drawable.recap_time_interval
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    CuriosityText(
                        modifier = Modifier,
                        value = "Your time interval",
                        textColor = CuriosityViolet,
                        textSize = 16.sp,
                        textWeight = FontWeight.SemiBold
                    )
                    CuriosityText(
                        modifier = Modifier,
                        value = viewModel.getIntervalFormattedValue(),
                        textColor = CuriosityViolet,
                        textSize = 16.sp,
                        textWeight = FontWeight.Normal
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            CuriositySvg(
                drawableResource = R.drawable.triple_cartoon
            )
            Spacer(modifier = Modifier.height(20.dp))
            CuriosityText(
                value = "You can change the settings whenever you want",
                textColor = CuriosityViolet,
                textSize = 14.sp,
                textWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(10.dp))
            CuriosityDefaultButton(
                value = "EXPLORE THE APP",
                valueColor = Color.White,
                backgroundColor = CuriosityBlue,
                onClick = {
                    viewModel.confirmSelectionExploreTheApp()
                }
            )
        }
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