package com.curiosity.presentation.profile.content

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.common.components.CuriosityCoupleTitle
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.presentation.profile.ProfileStates
import com.curiosity.presentation.profile.ProfileViewModel
import com.curiosity.presentation.sign_up.SignUpStates

/**
 * Composable function that displays an error screen for the profile flow.
 *
 * This function displays an error message and a button to reload the profile screen.
 *
 * @param navController The NavController used for navigation.
 * @param viewModel The viewModel used for control user data.
 * @param state The state of the profile screen, which includes the error message.
 */
@Composable
fun ProfileError(
    navController: NavController,
    viewModel: ProfileViewModel,
    state: ProfileStates
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 26.dp, top = 26.dp, start = 18.dp, end = 18.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        CuriosityCoupleTitle(
            titleText = "ERROR",
            subtitleText = state.currentUserLogoutError ?: "Internal error"
        )
        CuriosityDefaultButton(
            value = "Reload",
            onClick = {
                viewModel.updateStateValue(ProfileStates())
            }
        )
    }
}

@Preview
@Composable
fun ProfileErrorPreview(){
    ProfileError(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = ProfileStates()
    )
}