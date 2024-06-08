package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun ProfileHelpContainer(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        CuriosityText(
            modifier = Modifier,
            value = "Help",
            textColor = CuriosityViolet,
            textSize = 24.sp,
            textWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        CuriosityText(
            modifier = Modifier,
            value = "For any problem contact the support center.",
            textColor = CuriosityViolet,
            textSize = 14.sp,
            textWeight = FontWeight.Medium
        )
        CuriosityText(
            modifier = Modifier,
            value = "curiosity.support@mail.com",
            textColor = CuriosityViolet,
            textSize = 14.sp,
            textWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun ProfileHelpContainerPreview(){
    ProfileHelpContainer()
}