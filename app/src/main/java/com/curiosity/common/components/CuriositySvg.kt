package com.curiosity.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.curiosity.R


@Composable
fun CuriositySvg(
    modifier: Modifier = Modifier,
    drawableResource: Int = R.drawable.ic_launcher_background,
    height: Dp? = null
){
    val resource = remember { drawableResource }
    val painter = painterResource(id = resource)

    Image(
        modifier = if (height != null) Modifier.height(height) else modifier,
        painter = painter,
        contentDescription = "" + drawableResource
    )
}

@Preview
@Composable
fun CuriositySvgPreview(){
    CuriositySvg(
        drawableResource = R.drawable.cartoon_group,
    )
}
