package com.curiosity.presentation.areas_of_interest.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.curiosity.common.components.CuriositySvgButton
import com.curiosity.common.components.CuriosityText
import com.curiosity.presentation.areas_of_interest.AreasOfInterestViewModel
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityOrange
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun AreasOfInterestContent(
    navController: NavController,
    viewModel: AreasOfInterestViewModel
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CuriosityGray)
            .padding(
                bottom = 26.dp,
                top = 26.dp,
                start = 18.dp,
                end = 18.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Column {
            Row {
                CuriositySvgButton(
                    drawableResource = R.drawable.arrow
                ) {
                    navController.popBackStack()
                }
                CuriosityText(
                    modifier = Modifier.padding(start = 15.dp),
                    value = "Profile",
                    textColor = CuriosityViolet,
                    textSize = 16.sp,
                    textWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            CuriosityText(
                modifier = Modifier,
                value = "Update Areas of interest",
                textColor = CuriosityViolet,
                textSize = 16.sp,
                textWeight = FontWeight.SemiBold
            )
            CuriosityText(
                modifier = Modifier,
                value = "the elements with the black border are the ",
                textColor = CuriosityViolet,
                textSize = 14.sp,
                textWeight = FontWeight.Normal
            )
            CuriosityText(
                modifier = Modifier,
                value = "selected ones",
                textColor = CuriosityViolet,
                textSize = 14.sp,
                textWeight = FontWeight.Normal
            )

            CuriosityAreasOfInterest(
                areas = viewModel.getAreasOfInterest()
            )

            CuriosityDefaultButton(
                value = "CONFIRM",
                valueColor = Color.White,
                backgroundColor = CuriosityOrange,
                onClick = {
                    viewModel.confirmSelectionAreasOfInterest()
                }
            )
        }
    }
}

@Preview
@Composable
fun AreasOfInterestContentPreview(){
    AreasOfInterestContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
    )
}