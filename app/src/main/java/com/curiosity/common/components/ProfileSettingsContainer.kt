package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.R
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun ProfileSettingsContainer(
    onClickAreasOfInterest: () -> Unit,
    onClickIntervalBetweenNotification: () -> Unit,
    onClickChangePassword: () -> Unit,
    onClickLogout: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            CuriosityText(
                modifier = Modifier,
                value = "Settings",
                textColor = CuriosityViolet,
                textSize = 24.sp,
                textWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProfileButton(
                    width = 170.dp,
                    firstLineText = "Areas of",
                    secondLineText = "Interest",
                    icon = R.drawable.areas_of_interest,
                    paddingIcon = 20.dp,
                    onClick = onClickAreasOfInterest
                )
                Spacer(modifier = Modifier.width(10.dp))
                ProfileButton(
                    firstLineText = "Change",
                    secondLineText = "Password",
                    width = 170.dp,
                    icon = R.drawable.password,
                    paddingIcon = 15.dp,
                    onClick = onClickChangePassword
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProfileButton(
                    width = 240.dp,
                    firstLineText = "Interval between",
                    secondLineText = "Notification",
                    icon = R.drawable.intervall_notification,
                    paddingIcon = 22.dp,
                    onClick = onClickIntervalBetweenNotification
                )
                Spacer(modifier = Modifier.width(10.dp))
                ProfileLogoutButton(
                    icon = R.drawable.logout,
                    onClick = onClickLogout
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileSettingsContainerPreview(){
    ProfileSettingsContainer(
        onClickAreasOfInterest = {

        },
        onClickIntervalBetweenNotification = {

        },
        onClickChangePassword = {

        },
        onClickLogout = {

        }
    )
}