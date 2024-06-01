package com.curiosity.presentation.profile.content

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.R
import com.curiosity.common.components.CoinContainer
import com.curiosity.common.components.CuriositySvgButton
import com.curiosity.common.components.CuriosityText
import com.curiosity.common.components.ProfileHelpContainer
import com.curiosity.common.components.ProfileSettingsContainer
import com.curiosity.common.components.UserContainer
import com.curiosity.domain.model.User
import com.curiosity.presentation.app.routes.Routes
import com.curiosity.presentation.profile.ProfileStates
import com.curiosity.presentation.profile.ProfileViewModel
import com.curiosity.ui.theme.CuriosityViolet

/**
 * Composable function that represents the profile screen of the application.
 *
 * This function is responsible for displaying the user profile UI elements.
 */
@Composable
fun ProfileContent(
    navController: NavController,
    viewModel: ProfileViewModel,
    state: ProfileStates,
    user: User
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
    ){
        Row {
            CuriositySvgButton(
                drawableResource = R.drawable.arrow
            ) {
                navController.popBackStack()
            }
            CuriosityText(
                modifier = Modifier.padding(start = 15.dp),
                value = "home",
                textColor = CuriosityViolet,
                textSize = 16.sp,
                textWeight = FontWeight.SemiBold
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            CoinContainer(
                value = user.coins.toString()
            )
            UserContainer(
                modifier = Modifier.padding(bottom = 24.dp),
                username = user.username ?: "no user"
            )
        }
        Column {
            ProfileSettingsContainer(
                onClickAreasOfInterest = {
                    navController.navigate(Routes.AreasOfInterestScreen.route)
                },
                onClickIntervalBetweenNotification = {

                },
                onClickChangePassword = {

                },
                onClickLogout = {
                    viewModel.logOut()
                },
                intervalFormatted = "current: " + user.interval + if(user.isMinutes) " m" else " h"
            )
            Spacer(modifier = Modifier.height(15.dp))
            ProfileHelpContainer()
        }
    }
}

/**
 * Preview function for the ProfileContent composable.
 *
 * This function is used to display a preview of the ProfileContent composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun ProfileContentPreview(){
    ProfileContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = ProfileStates(),
        user = User()
    )
}