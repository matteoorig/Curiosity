package com.curiosity.presentation.sign_up.content

/**
 * @author matteooriggi
 */

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import com.curiosity.common.components.CuriosityCoupleTitle
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.common.components.CuriosityDivider
import com.curiosity.common.components.CuriosityPasswordField
import com.curiosity.common.components.CuriositySvgButton
import com.curiosity.common.components.CuriosityText
import com.curiosity.common.components.CuriosityTextField
import com.curiosity.common.components.SignWithItem
import com.curiosity.presentation.app.routes.Routes
import com.curiosity.presentation.sign_up.SignUpStates
import com.curiosity.presentation.sign_up.SignUpViewModel
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityViolet

/**
 * Composable function that represents the content of the sign-up screen.
 *
 * This function is responsible for displaying the UI elements for the user to sign up.
 */
@Composable
fun SignUpContent(
    navController: NavController,
    viewModel: SignUpViewModel,
    state: SignUpStates,
    usernameValue: String,
    mailValue: String,
    passwordValue: String,
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
        Column {
            // CuriosityProgressBar(widthsList = viewModel.getListScope())
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                CuriositySvgButton(
                    drawableResource = R.drawable.arrow,
                    height = 35.dp
                ) {
                    navController.popBackStack(
                        route = Routes.SignUpScreen.route,
                        inclusive = true,
                        saveState = false
                    )
                }
            }

            CuriosityCoupleTitle(
                titleText = "Hi!",
                subtitleText = "Sign up to continue"
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        Column {
            CuriosityTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                text = usernameValue,
                onTextChange = {
                    viewModel.updateUsernameValue(it)
                },
                fontSize = 20.sp,
                placeholder = "Username",
                valueColor = CuriosityViolet,
                backgroundColor = CuriosityGray,
                helperText = true,
                helperTextColor = CuriosityViolet,
                maxLength = 32
            )
            Spacer(modifier = Modifier.height(10.dp))
            CuriosityTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                text = mailValue,
                onTextChange = {
                    viewModel.updateMailValue(it)
                },
                fontSize = 20.sp,
                placeholder = "Mail",
                valueColor = CuriosityViolet,
                backgroundColor = CuriosityGray,
                helperText = true,
                helperTextColor = CuriosityViolet,
                maxLength = 32
            )
            Spacer(modifier = Modifier.height(10.dp))
            CuriosityPasswordField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                text = passwordValue,
                onTextChange = {
                    if (it.length <= 32)
                        viewModel.updatePasswordValue(it)
                },
                fontSize = 20.sp,
                placeholder = "Password",
                valueColor = CuriosityViolet,
                backgroundColor = CuriosityGray,
                helperText = true,
                helperTextColor = CuriosityViolet,
                maxLength = 32
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
        Column {
            CuriosityDefaultButton(
                value = "Sign Up",
                valueColor = Color.White,
                backgroundColor = CuriosityViolet,
                onClick = {
                    viewModel.signUpUser()
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            CuriosityDivider(
                text = "or",
                textColor = CuriosityViolet
            )
            Spacer(modifier = Modifier.height(15.dp))
            CuriosityText(
                value = "you can sign up with",
                textColor = CuriosityViolet,
                textSize = 16.sp,
                textWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                SignWithItem(
                    drawableResource = R.drawable.google_icon,
                    onClick = { Log.d("SignWithItem", "Google sign in") }
                )
                SignWithItem(
                    drawableResource = R.drawable.facebook_icon,
                    onClick = { Log.d("SignWithItem", "Facebook sign in") }
                )
                SignWithItem(
                    drawableResource = R.drawable.apple_icon,
                    onClick = { Log.d("SignWithItem", "Apple sign in") }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

/**
 * Preview function for the SignUpContent composable.
 *
 * This function is used to display a preview of the SignUpContent composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun SignUpContentPreview(){
    SignUpContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = SignUpStates(),
        usernameValue = "",
        passwordValue = "",
        mailValue = ""
    )
}