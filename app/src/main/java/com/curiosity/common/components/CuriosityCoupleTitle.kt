package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun CuriosityCoupleTitle(
    titleText : String = "Default title",
    subtitleText : String = "Default subtitle",
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        CuriosityText(
            value = titleText,
            textColor = CuriosityViolet,
            textSize = 48.sp,
        )
        CuriosityText(
            value = subtitleText,
            textColor = CuriosityViolet,
            textSize = 20.sp,
            textWeight = FontWeight.Medium,
            maxLines = 5
        )
    }

}


@Preview(showBackground = true)
@Composable
fun CoupleTitlePreview(){
    CuriosityCoupleTitle()
}