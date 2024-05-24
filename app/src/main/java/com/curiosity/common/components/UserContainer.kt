package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.curiosity.R
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun UserContainer(
    username: String,
    modifier: Modifier? = null
){
    Column(
        modifier = modifier ?: Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CuriositySvg(
            drawableResource = R.drawable.profile
        )
        CuriosityText(
            modifier = Modifier,
            value = username,
            textColor = CuriosityViolet,
            textWeight = FontWeight.SemiBold,
            textSize = 20.sp,
        )
    }
}

@Preview
@Composable
fun UserContainerPreview(){
    UserContainer(
        username = "username"
    )
}