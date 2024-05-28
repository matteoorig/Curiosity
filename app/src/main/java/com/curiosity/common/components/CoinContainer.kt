package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.R
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun CoinContainer(
    value: String,
    valueSize: TextUnit = 128.sp,
    modifier: Modifier? = null
){
    Row{
        CuriosityText(
            modifier = Modifier,
            value = value,
            textColor = CuriosityViolet,
            textSize = valueSize,
            textWeight = FontWeight.SemiBold
        )
        CuriositySvg(
            modifier = Modifier
                .align(Alignment.Bottom)
                .then(modifier ?: Modifier.padding(bottom = 20.dp)),
            drawableResource = R.drawable.trophy
        )
    }


}

@Preview
@Composable
fun CoinContainerPreview(){
    CoinContainer(
        value = "98"
    )
}