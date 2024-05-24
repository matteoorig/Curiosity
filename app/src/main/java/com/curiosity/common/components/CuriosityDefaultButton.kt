package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.common.fonts.Poppins
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun CuriosityDefaultButton(
    value: String,
    valueColor: Color = Color.Black,
    height: Dp? = null,
    width: Dp? = null,
    backgroundColor: Color? = null,
    borderColor: Color? = null,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .widthIn(min = width ?: LocalConfiguration.current.screenWidthDp.dp)
                .heightIn(min = height ?: 40.dp)
                .border(
                    4.dp,
                    color = borderColor ?: Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor =  backgroundColor ?: Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = onClick
        ) {
            Text(
                text = value,
                color = valueColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )
        }
    }
}


@Preview
@Composable
fun CuriosityDefaultButtonPreview(){
    CuriosityDefaultButton(
        value = "Default Text",
        valueColor = Color.White,
        backgroundColor = CuriosityViolet,
        onClick = {

        }
    )
}

@Preview
@Composable
fun CuriosityBorderedButtonPreview(){
    CuriosityDefaultButton(
        value = "Default Text",
        valueColor = CuriosityViolet,
        borderColor = CuriosityViolet,
        onClick = {

        }
    )
}