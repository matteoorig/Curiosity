package com.curiosity.presentation.sign_in.content

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
import androidx.compose.ui.res.colorResource
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
import com.curiosity.presentation.sign_in.SignInStates
import com.curiosity.presentation.sign_in.SignInViewModel
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityViolet

/**
 * Composable function that represents the content of the sign-in screen.
 *
 * This function is responsible for displaying the UI elements for the user to sign in.
 */
@Composable
fun SignInContent(
    navController: NavController,
    viewModel: SignInViewModel,
    state: SignInStates,
    mailValue: String,
    passwordValue: String,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 26.dp, top = 26.dp, start = 18.dp, end = 18.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column{
            // CuriosityProgressBar(widthsList = viewModel.getListScope())
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                CuriositySvgButton(
                    drawableResource = R.drawable.arrow,
                    height = 35.dp,
                    onClick = {
                        navController.popBackStack(
                            route = Routes.SignInScreen.route,
                            inclusive = true,
                            saveState = false
                        )
                    }
                )
            }
            CuriosityCoupleTitle(
                titleText = "Hi!",
                subtitleText = "Log in to continue"
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        Column {
            CuriosityTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                text = mailValue,
                onTextChange = { viewModel.updateMailValue(it) },
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
                    if (it.length <= 32) viewModel.updatePasswordValue(it)
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
                value = "Sign In",
                valueColor = colorResource(id = R.color.white),
                backgroundColor = CuriosityViolet,
                onClick = {
                    viewModel.signInUser()
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            CuriosityDivider(
                text = "or",
                textColor = CuriosityViolet
            )
            Spacer(modifier = Modifier.height(15.dp))
            CuriosityText(
                value = "you can sign in with",
                textColor = CuriosityViolet,
                textSize = 16.sp,
                textWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
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
 * Preview function for the SignInContent composable.
 *
 * This function is used to display a preview of the SignInContent composable in Android Studio.
 * It helps in visualizing the UI without running the application on a device or emulator.
 */
@Preview
@Composable
fun SignInContentPreview(){
    SignInContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = SignInStates(),
        mailValue = "",
        passwordValue = ""
    )
}