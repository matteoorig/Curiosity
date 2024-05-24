package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.curiosity.R


@Composable
fun CuriositySvgButton(
    modifier: Modifier = Modifier,
    drawableResource: Int = R.drawable.ic_launcher_background,
    height: Dp? = null,
    onClick: (() -> Unit)? = null
){
    val clickModifier = if (onClick != null) {
        Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            onClick()
        }
    } else Modifier

    val heightModifier = if (height != null) {
        Modifier.height(height)
    } else Modifier

    Image(
        modifier = modifier.then(heightModifier).then(clickModifier),
        painter = painterResource(id = drawableResource),
        contentDescription = "svg"
    )
}

@Preview
@Composable
fun CuriositySvgButtonPreview(){
    CuriositySvgButton(
        drawableResource = R.drawable.arrow,
        onClick = {
            Log.d("CuriositySvgButtonPreview", "Button clicked")
        }
    )
}
