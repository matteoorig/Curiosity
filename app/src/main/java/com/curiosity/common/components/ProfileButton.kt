package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.R
import com.curiosity.common.fonts.Poppins
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityViolet

@Composable
fun ProfileButton(
    firstLineText: String? = null,
    secondLineText:String? = null,
    subTitle: String? = null,
    icon: Int = R.drawable.ic_launcher_background,
    height: Dp? = null,
    width: Dp? = null,
    paddingIcon: Dp? = null,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .width(width ?: 150.dp)
            .height(height ?: 100.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = CuriosityViolet,
                    bounded = true
                )
            )
            .background(CuriosityGray)
            .border(
                4.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = firstLineText ?: "Default",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins,
                color = CuriosityViolet
            )
            Text(
                text = secondLineText ?: "Default",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins,
                color = CuriosityViolet
            )
            if(subTitle != null){
                Text(
                    text = subTitle,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Poppins,
                    color = CuriosityViolet
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingIcon ?: 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                tint = CuriosityViolet
            )
        }
    }
}



@Preview
@Composable
fun ProfileButtonPreview(){
    ProfileButton(
        icon = R.drawable.intervall_notification,
        width = 200.dp,
        height = 100.dp,
        paddingIcon = 20.dp,
        subTitle = "current: 15",
        onClick = {

        }
    )
}