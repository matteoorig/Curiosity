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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.R
import com.curiosity.common.components.CuriosityAreasOfInterest
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.common.components.CuriositySvg
import com.curiosity.common.components.CuriosityText
import com.curiosity.presentation.on_boarding.OnBoardingStates
import com.curiosity.presentation.on_boarding.OnBoardingViewModel
import com.curiosity.ui.theme.CuriosityViolet
import com.curiosity.ui.theme.CuriosityYellow

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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                CuriosityText(
                    modifier = Modifier,
                    value = "Select the areas",
                    textSize = 24.sp,
                    textWeight = FontWeight.Medium,
                    textColor = CuriosityViolet,
                )
                CuriosityText(
                    modifier = Modifier,
                    value = "of interest you prefer",
                    textSize = 24.sp,
                    textWeight = FontWeight.Medium,
                    textColor = CuriosityViolet
                )
            }
            CuriositySvg(
                drawableResource = R.drawable.single_yellow_cartoon
            )
        }

        CuriosityAreasOfInterest(
            areas = viewModel.getAreasOfInterest()
        )

        CuriosityDefaultButton(
            value = "NEXT",
            valueColor = colorResource(id = R.color.white),
            backgroundColor = CuriosityYellow,
            onClick = {

            }
        )
    }

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