package com.curiosity.presentation.intro.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.curiosity.presentation.app.routes.Routes
import com.curiosity.presentation.intro.IntroStates
import com.curiosity.presentation.intro.IntroViewModel
import com.curiosity.ui.theme.CuriosityViolet

/**
 * Composable function that represents the content of the intro screen.
 *
 * This function is responsible for displaying the UI elements that make up the intro screen.
 */
@Composable
fun IntroContent(
    navController: NavController,
    viewModel: IntroViewModel,
    state: IntroStates
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 26.dp,
                top = 26.dp,
                start = 18.dp,
                end = 18.dp
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CuriosityCoupleTitle(
            titleText = "CURIOSITY",
            subtitleText = "Always stay updated \n" +
                    "on the curiosities of the world"
        )
        CuriositySvg(
            drawableResource = R.drawable.cartoon_group
        )

        Column {
            CuriosityDefaultButton(
                value = "Sign In",
                valueColor = CuriosityViolet,
                borderColor = CuriosityViolet,
                onClick = {
                    navController.navigate(Routes.SignInScreen.route)
                }
            )
            Spacer(modifier = Modifier.height(13.dp))
            CuriosityDefaultButton(
                value = "Sign Up",
                valueColor = Color.White,
                backgroundColor = CuriosityViolet,
                onClick = {
                    navController.navigate(Routes.SignUpScreen.route)
                }
            )
        }
    }
}

/**
 * Preview function for the IntroContent composable.
 *
 * This function is used to display a preview of the IntroContent composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun IntroScreenContentPreview() {
    IntroContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = IntroStates()
    )
}