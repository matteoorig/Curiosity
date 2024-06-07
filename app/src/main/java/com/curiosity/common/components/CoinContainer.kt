package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.R
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun CoinContainer(
    count: Int,
    valueSize: TextUnit = 68.sp,
    modifier: Modifier? = null
){
    var oldCount by remember {
        mutableIntStateOf(count)
    }

    SideEffect {
        oldCount = count
    }


    Row{
        val countString = count.toString()
        val oldCountString = oldCount.toString()
        for(i in countString.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]
            val char = if (oldChar == newChar) {
                oldCountString[i]
            } else {
                countString[i]
            }

            AnimatedContent(
                targetState = char,
                label = "",
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }
            ) {char ->
                CuriosityText(
                    modifier = Modifier,
                    value = char.toString(),
                    textColor = CuriosityViolet,
                    textSize = valueSize,
                    textWeight = FontWeight.SemiBold,
                    softWrap = false
                )
            }
        }
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
    var value by remember {
        mutableStateOf(15)
    }
    Column {
        CoinContainer(
            count = value
        )
        Button(onClick = {
            value += 1
        }) {

        }
    }
}
