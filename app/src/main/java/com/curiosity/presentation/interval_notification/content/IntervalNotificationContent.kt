package com.curiosity.presentation.interval_notification.content

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
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.common.components.CuriosityIntervalNotification
import com.curiosity.common.components.CuriositySvgButton
import com.curiosity.common.components.CuriosityText
import com.curiosity.presentation.interval_notification.IntervalNotificationStates
import com.curiosity.presentation.interval_notification.IntervalNotificationViewModel
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityOrange
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun IntervalNotificationContent(
    navController: NavController,
    viewModel: IntervalNotificationViewModel,
    isMinutes: Boolean,
    interval: Int,
    state: IntervalNotificationStates
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
            Column {
                CuriosityText(
                    modifier = Modifier,
                    value = "Update Interval of Notifications",
                    textColor = CuriosityViolet,
                    textSize = 16.sp,
                    textWeight = FontWeight.SemiBold
                )
                CuriosityText(
                    modifier = Modifier,
                    value = "Change the value by choosing between minutes",
                    textColor = CuriosityViolet,
                    textSize = 14.sp,
                    textWeight = FontWeight.Normal
                )
                CuriosityText(
                    modifier = Modifier,
                    value = "or hours. If you hold down you go faster!",
                    textColor = CuriosityViolet,
                    textSize = 14.sp,
                    textWeight = FontWeight.Normal
                )
            }
        }

        if(state.loadCurrentUserIntervalNotificationSuccess){
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
        }

        CuriosityDefaultButton(
            value = "CONFIRM",
            valueColor = Color.White,
            backgroundColor = CuriosityOrange,
            onClick = {
                viewModel.confirmSelectionIntervalNotification()
            }
        )
    }
}

@Preview
@Composable
fun IntervalNotificationContentPreview(){
    IntervalNotificationContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        isMinutes = true,
        interval = 10,
        state = IntervalNotificationStates()
    )
}