package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.curiosity.common.fonts.Poppins
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun CuriosityText(
    modifier: Modifier? = null,
    value: String = "Default text",
    textColor: Color = Color.Black,
    textSize: TextUnit = 18.sp,
    textWeight: FontWeight = FontWeight.Bold,
){
    Row(
        modifier = modifier ?: Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = value,
            color = textColor,
            fontSize = textSize,
            fontWeight = textWeight,
            fontFamily = Poppins,
            textAlign = TextAlign.Center,
        )
    }
}


@Preview
@Composable
fun CuriosityTitlePreview(){
    CuriosityText(
        value = "CURIOSITY",
        textColor = CuriosityViolet,
        textSize = 48.sp,
    )
}