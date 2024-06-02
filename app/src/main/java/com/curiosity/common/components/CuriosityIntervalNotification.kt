package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.R
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityViolet
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CuriosityIntervalNotification(
    isMinutes: Boolean,
    interval: Int,
    updateIsMinutes: (Boolean) -> Unit,
    updateInterval: (Int) -> Unit
){
    
    var myInterval = remember {
        mutableIntStateOf(interval)
    }

    var currentIsMinutes = remember {
        mutableStateOf(isMinutes)
    }

    fun setIntervalValue(newValue: Int){
        myInterval.value = newValue
        updateInterval(myInterval.value)
    }

    fun setIsMinutesValue(newValue: Boolean){
        currentIsMinutes.value = newValue
        updateIsMinutes(currentIsMinutes.value)
    }

    fun incrementInterval(isMinutes: MutableState<Boolean>, interval: MutableIntState){
        if(isMinutes.value){
            if(interval.value == 60){
                setIntervalValue(1)
            }else{
                setIntervalValue(interval.value+1)
            }
        }else{
            if(interval.value == 24){
                setIntervalValue(1)
            }else{
                setIntervalValue(interval.value+1)
            }
        }
    }

    fun decrementInterval(isMinutes: MutableState<Boolean>, interval: MutableIntState){
        if(isMinutes.value){
            if(interval.value == 1){
                setIntervalValue(60)
            } else{
                setIntervalValue(interval.value-1)
            }
        }else{
            if(interval.value == 1){
                setIntervalValue(24)
            }else{
                setIntervalValue(interval.value-1)
            }
        }
    }
    var isPressed by remember { mutableStateOf(false) }
    var incrementJob by remember { mutableStateOf<Job?>(null) }

    val coroutineScope = rememberCoroutineScope()


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CuriosityGray)
                .clickable {
                    setIsMinutesValue(!currentIsMinutes.value)
                    setIntervalValue(1)
                },
            horizontalArrangement = Arrangement.SpaceAround

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (currentIsMinutes.value) CuriosityViolet else Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                CuriosityText(
                    value = "minutes",
                    textColor = if (currentIsMinutes.value) Color.White else CuriosityViolet,
                    textWeight = FontWeight.Medium,
                    textSize = 24.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (!currentIsMinutes.value) CuriosityViolet else Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                CuriosityText(
                    value = "hour",
                    textColor = if (!currentIsMinutes.value) Color.White else CuriosityViolet,
                    textWeight = FontWeight.Medium,
                    textSize = 24.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CuriositySvgButton(
                drawableResource = R.drawable.intervall_button_left,
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            isPressed = true
                            incrementJob = coroutineScope.launch {
                                while (isPressed) {
                                    decrementInterval(currentIsMinutes, myInterval)
                                    delay(100)
                                }
                            }
                        },
                        onPress = {
                            tryAwaitRelease()
                            isPressed = false
                            incrementJob?.cancel()
                            incrementJob = null
                        },
                        onTap = {
                            decrementInterval(currentIsMinutes, myInterval)
                        }
                    )
                },
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.7F)
                    .height(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(CuriosityGray),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                CuriosityText(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .align(Alignment.CenterVertically),
                    value = interval.toString(),
                    textSize = 48.sp,
                    textWeight = FontWeight.Medium,
                    textColor = CuriosityViolet
                )
                CuriosityText(
                    modifier = Modifier.padding(bottom = 10.dp),
                    value = if(currentIsMinutes.value) "m" else "h",
                    textSize = 24.sp,
                    textWeight = FontWeight.Medium,
                    textColor = CuriosityViolet
                )
            }
            CuriositySvgButton(
                drawableResource = R.drawable.intervall_button_right,
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            isPressed = true
                            incrementJob = coroutineScope.launch {
                                while (isPressed) {
                                    incrementInterval(currentIsMinutes, myInterval)
                                    delay(100)
                                }
                            }
                        },
                        onPress = {
                            tryAwaitRelease()
                            isPressed = false
                            incrementJob?.cancel()
                            incrementJob = null
                        },
                        onTap = {
                            incrementInterval(currentIsMinutes, myInterval)
                        }
                    )
                },
            )
        }


    }

}



@Preview
@Composable
fun CuriosityIntervalNotificationPreview(){

    var isMinutes = remember {
        mutableStateOf(false)
    }

    fun updateIsMinutesValue(newValue: Boolean){
        isMinutes.value = newValue
    }

    var interval = remember {
        mutableIntStateOf(1)
    }

    fun updateIntervalValue(newValue: Int){
        interval.value = newValue
    }

    CuriosityIntervalNotification(
        isMinutes = isMinutes.value,
        interval = interval.value,
        updateIsMinutes = {
            updateIsMinutesValue(it)
        },
        updateInterval = {
            updateIntervalValue(it)
        }
    )
}