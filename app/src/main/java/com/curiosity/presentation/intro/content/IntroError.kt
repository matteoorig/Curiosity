package com.curiosity.presentation.intro.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.common.components.CuriosityCoupleTitle
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.presentation.app.routes.Routes
import com.curiosity.presentation.intro.IntroStates

@Composable
fun IntroError(
    navController: NavController,
    state: IntroStates
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 26.dp, top = 26.dp, start = 18.dp, end = 18.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        CuriosityCoupleTitle(
            titleText = "ERROR",
            subtitleText = state.currentUserExistError ?: "Internal error"
        )
        CuriosityDefaultButton(
            value = "Go to the first page",
            onClick = {
                navController.popBackStack()
            }
        )
    }
}

@Preview
@Composable
fun IntroErrorPreview(){
    IntroError(
        navController = rememberNavController(),
        state = IntroStates()
    )
}