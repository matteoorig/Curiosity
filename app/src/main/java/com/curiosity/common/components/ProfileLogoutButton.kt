package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.curiosity.R
import com.curiosity.ui.theme.CuriosityRed

@Composable
fun ProfileLogoutButton(
    onClick: () -> Unit,
    icon: Int = R.drawable.ic_launcher_background
){
    Button(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .border(
                4.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = CuriosityRed
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier
                    .width(50.dp)
            )
        }
    }
}

@Preview
@Composable
fun ProfileLogoutButtonPreview(){
    ProfileLogoutButton(
        onClick = {

        }
    )
}