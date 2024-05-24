package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.common.fonts.Poppins
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun CuriosityDivider(
    modifier: Modifier? = null,
    text:String,
    textColor: Color = Color.Black,
    textSize: TextUnit = 18.sp,
    textWeight: FontWeight = FontWeight.Normal,
    lineColor: Color = textColor
){

    Row(
        modifier = modifier ?: Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = 110.dp)
                .height(2.dp)
                .background(lineColor)
        )
        Text(
            text = text,
            color = textColor,
            fontSize = textSize,
            fontWeight = textWeight,
            fontFamily = Poppins,
        )
        Box(
            modifier = Modifier
                .widthIn(min = 110.dp)
                .height(2.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(lineColor)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CuriosityDividerPreview(){
    CuriosityDivider(
        text = "Default",
        textColor = CuriosityViolet,

    )
}