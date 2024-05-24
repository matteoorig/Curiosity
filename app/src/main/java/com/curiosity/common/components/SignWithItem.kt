package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.curiosity.R

@Composable
fun SignWithItem(
    modifier: Modifier? = null,
    drawableResource: Int,
    onClick: () -> Unit
){
    val resource = remember { drawableResource }
    val painter = painterResource(id = resource)

    IconButton(
        modifier =Modifier
            .height(50.dp)
            .width(50.dp),
        onClick = onClick,
    ){
        Image(
            painter = painter,
            contentDescription = "" + drawableResource
        )
    }
}


@Preview
@Composable
fun SignWithItemPreview(){
    SignWithItem(
        drawableResource = R.drawable.google_icon,
        onClick = {

        }
    )
}