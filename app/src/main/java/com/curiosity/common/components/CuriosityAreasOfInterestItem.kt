package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.R
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityViolet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CuriosityAreasOfInterestItem(
    value: String,
    icon: Int,
    onClick: () -> Unit,
    isClicked: Boolean
) {
    val isClickedState = remember { mutableStateOf(isClicked) }
    val coroutineScope = rememberCoroutineScope()

    val size = if (isClickedState.value) 155.dp else 150.dp
    val animatedSize by animateDpAsState(targetValue = size, label = "")

    Row(
        modifier = Modifier
            .clickable {
                isClickedState.value = !isClickedState.value
                onClick()
                coroutineScope.launch {
                    delay(500)
                }
            }
            .clip(RoundedCornerShape(10.dp))
            .background(CuriosityGray)
            .border(
                6.dp,
                color = if (isClickedState.value) CuriosityViolet else Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .size(animatedSize)
                .padding(15.dp)
        ) {
            CuriosityText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp),
                value = value,
                textColor = CuriosityViolet,
                textSize = 14.sp,
                textWeight = FontWeight.SemiBold,
            )
            Icon(
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 5.dp),
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = CuriosityViolet
            )
        }
    }
}

@Preview
@Composable
fun CuriosityAreasOfInterestItemPreview() {
    val itemData = CuriosityAreasOfInterestItemData(
        idx = 0,
        value = "AREA",
        icon = R.drawable.areas_of_interest,
        isClicked = false,
    )

    CuriosityAreasOfInterestItem(
        value = itemData.value,
        icon = itemData.icon,
        isClicked = itemData.isClicked,
        onClick = {
            itemData.isClicked = !itemData.isClicked
        }
    )
}
