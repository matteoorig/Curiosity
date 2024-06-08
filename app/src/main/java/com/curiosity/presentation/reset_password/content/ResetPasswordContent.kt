package com.curiosity.presentation.reset_password.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.curiosity.common.components.CuriositySvgButton
import com.curiosity.common.components.CuriosityText
import com.curiosity.common.components.CuriosityTextField
import com.curiosity.presentation.reset_password.ResetPasswordStates
import com.curiosity.presentation.reset_password.ResetPasswordViewModel
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun ResetPasswordContent(
    navController: NavController,
    viewModel: ResetPasswordViewModel,
    state: ResetPasswordStates,
    mailValue: String
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 26.dp, top = 26.dp, start = 18.dp, end = 18.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column{
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                CuriositySvgButton(
                    drawableResource = R.drawable.arrow,
                    height = 35.dp,
                    onClick = {
                        viewModel.updateStateValue(
                            ResetPasswordStates(
                            sendResetPasswordEmailSuccessful = true)
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            CuriosityText(
                value = "Insert your email",
                textColor = CuriosityViolet,
                textSize = 36.sp,
                textWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            CuriosityText(
                value = "Click on the link sent to your email and change your password",
                textColor = CuriosityViolet,
                textSize = 20.sp,
                textWeight = FontWeight.Normal,
                maxLines = 5
            )
        }

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

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CuriosityText(
                    modifier = Modifier,
                    value = "Didn't receive the email?",
                    textColor = CuriosityViolet,
                    textSize = 12.sp,
                    textWeight = FontWeight.Normal,
                    maxLines = 5
                )
                Spacer(modifier = Modifier.width(5.dp))
                CuriosityText(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ){ viewModel.sendResetEmail() },
                    value = "resend",
                    textColor = CuriosityViolet,
                    textSize = 12.sp,
                    textWeight = FontWeight.Bold,
                    maxLines = 5
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            CuriosityDefaultButton(
                value = "CONFIRM",
                valueColor = Color.White,
                backgroundColor = CuriosityViolet,
                onClick = {
                    viewModel.sendResetEmail()
                }
            )
        }
    }
}

@Preview
@Composable
fun ResetPasswordContentPreview(){
    ResetPasswordContent(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        state = ResetPasswordStates(),
        mailValue = ""
    )
}