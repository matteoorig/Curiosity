package com.curiosity.presentation.intro.content

/**
 * @author matteooriggi
 */

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.curiosity.R
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.common.components.CuriositySvg
import com.curiosity.common.components.CuriosityText
import com.curiosity.presentation.intro.IntroStates
import com.curiosity.presentation.intro.IntroViewModel
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun IntroRequestNotificationPermission(
    viewModel: IntroViewModel
){

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.confirmNotificationPermission(isGranted)
        }
    )

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

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
        Column {
            CuriosityText(
                value = "Oh, I'm sorry",
                textColor = CuriosityViolet,
                textSize = 48.sp,
                textWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                CuriositySvg(
                    modifier = Modifier.size(70.dp),
                    drawableResource = R.drawable.single_yellow_cartoon
                )
                CuriosityText(
                    value = "Without notifications you can't use the app.",
                    textColor = CuriosityViolet,
                    textSize = 14.sp,
                    textWeight = FontWeight.Normal,
                    maxLines = 5
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            CuriosityDefaultButton(
                value = "Reload",
                valueColor = CuriosityViolet,
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun RequestNotificationPermissionPreview(){
    IntroRequestNotificationPermission(
        viewModel = hiltViewModel()
    )
}