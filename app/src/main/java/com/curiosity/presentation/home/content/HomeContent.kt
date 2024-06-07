package com.curiosity.presentation.home.content

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.curiosity.R
import com.curiosity.common.components.CoinContainer
import com.curiosity.common.components.CuriosityDefaultButton
import com.curiosity.common.components.CuriositySvg
import com.curiosity.common.components.CuriositySvgButton
import com.curiosity.common.components.CuriosityText
import com.curiosity.domain.model.CuriosityData
import com.curiosity.domain.model.User
import com.curiosity.presentation.app.routes.Routes
import com.curiosity.presentation.home.HomeStates
import com.curiosity.presentation.home.HomeViewModel
import com.curiosity.presentation.home.content.composable.ShakeConfig
import com.curiosity.presentation.home.content.composable.ShakeController
import com.curiosity.presentation.home.content.composable.shake
import com.curiosity.ui.theme.CuriosityBlue
import com.curiosity.ui.theme.CuriosityDarkViolet
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityGreen
import com.curiosity.ui.theme.CuriosityOrange
import com.curiosity.ui.theme.CuriosityPink
import com.curiosity.ui.theme.CuriosityViolet
import com.curiosity.ui.theme.CuriosityYellow

@Composable
fun HomeContent(
    navController: NavController,
    viewModel: HomeViewModel,
    state: HomeStates,
    user: User,
    curiosity: CuriosityData
){

    val draggableOffset: Int = 5
    var horizontalCount by remember {
        mutableIntStateOf(0)
    }

    val shakeController = remember { ShakeController() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CuriosityGray)
            .padding(
                bottom = 26.dp,
                top = 26.dp,
                start = 18.dp,
                end = 18.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CoinContainer(
                    count = user.coins,
                    valueSize = 32.sp,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .size(10.dp)
                )
                CuriositySvgButton(
                    modifier = Modifier.size(32.dp),
                    drawableResource = R.drawable.profile,
                    onClick = {
                        navController.navigate(Routes.ProfileScreen.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            CuriosityText(
                value = "Generate a new \n" +
                        "curiosity",
                textColor = CuriosityViolet,
                textSize = 36.sp,
                textWeight = FontWeight.Bold,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(15.dp))
            CuriosityText(
                value = "tap to in the center of the card\n" +
                        "to generate a new one",
                textColor = CuriosityViolet,
                textSize = 14.sp,
                textWeight = FontWeight.Medium,
                maxLines = 2
            )
        }
        Row(
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .shake(shakeController = shakeController)
                .border(10.dp, CuriosityViolet, RoundedCornerShape(35.dp))
                .background(
                    color = when (user.level) {
                        1 -> CuriosityYellow
                        2 -> CuriosityPink
                        3 -> CuriosityGreen
                        4 -> CuriosityDarkViolet
                        5 -> CuriosityOrange
                        6 -> CuriosityBlue
                        else -> Color.Transparent
                    },
                    shape = RoundedCornerShape(35.dp)
                )
                .padding(20.dp)
                .shake(shakeController)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            // Callback click on tap item
                            /**
                             * viewModel.getCuriosity()
                             * shakeController.shake(
                             *  ShakeConfig(
                             *    iterations = 1,
                             *    intensity = 1_000f,
                             *    rotateX = 20f,
                             *    translateY = -20f,
                             *  )
                             * )
                             */
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { horizontalCount = 0 },
                        onHorizontalDrag = { change, dragAmount ->
                            horizontalCount += 1
                            if (horizontalCount > draggableOffset) {
                                // Callback click on drag item
                                viewModel.discardCuriosity()
                                shakeController.shake(
                                    ShakeConfig(
                                        iterations = 4,
                                        intensity = 2_000f,
                                        rotateY = 5f,
                                        translateX = 20f,
                                    )
                                )
                            }
                        }
                    )
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (state.curiosityIsLoading){
                CircularProgressIndicator()
            }else{
                CuriosityText(
                    value = curiosity.value,
                    textColor = Color.White,
                    textSize = 18.sp,
                    textWeight = FontWeight.Bold,
                    maxLines = 8
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(user.level) {
                1 -> {
                    CuriositySvg(
                        drawableResource = R.drawable.single_yellow_cartoon
                    )
                }
                2 -> {
                    CuriositySvg(
                        drawableResource = R.drawable.single_pink_cartoon
                    )
                }
                3 -> {
                    CuriositySvg(
                        drawableResource = R.drawable.single_green_cartoon
                    )
                }
                4 -> {
                    CuriositySvg(
                        drawableResource = R.drawable.single_violet_cartoon
                    )
                }
                5 -> {
                    CuriositySvg(
                        drawableResource = R.drawable.single_orange_cartoon
                    )
                }
                6 -> {
                    CuriositySvg(
                        drawableResource = R.drawable.single_blue_cartoon
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CuriosityDefaultButton(
                    modifier = Modifier,
                    value = "I don't know",
                    valueColor = Color.White,
                    valueSize = 18.sp,
                    valueWeight = FontWeight.SemiBold,
                    height = 70.dp,
                    width = 160.dp,
                    backgroundColor = CuriosityViolet,
                    onClick = {
                        // Callback click i don't know
                        viewModel.notKnowCuriosity()
                    }
                )
                CuriosityText(
                    modifier = Modifier,
                    value = "VS",
                    textColor = CuriosityViolet,
                    textSize = 18.sp,
                    textWeight = FontWeight.Normal
                )
                CuriosityDefaultButton(
                    modifier = Modifier,
                    value = "I Know",
                    valueColor = Color.White,
                    valueSize = 18.sp,
                    valueWeight = FontWeight.SemiBold,
                    height = 70.dp,
                    width = 160.dp,
                    backgroundColor = CuriosityViolet,
                    onClick = {
                        // Callback click i know
                        viewModel.knowCuriosity()
                    }
                )
            }
        }
    }

}

fun getBackgroundFromUserLevel(user: User): Color {
    return when(user.level){
        1 ->  CuriosityYellow
        2 ->  CuriosityPink
        3 ->  CuriosityGreen
        4 ->  CuriosityDarkViolet
        5 ->  CuriosityOrange
        6 ->  CuriosityBlue
        else -> {
            Color.Transparent
        }
    }
}

fun getDrawableFromUserLevel(user: User): Int {
    return when(user.level){
        1 ->  R.drawable.single_yellow_cartoon
        2 ->  R.drawable.single_pink_cartoon
        3 ->  R.drawable.single_green_cartoon
        4 ->  R.drawable.single_violet_cartoon
        5 ->  R.drawable.single_orange_cartoon
        6 ->  R.drawable.single_blue_cartoon
        else -> {
            R.drawable.single_yellow_cartoon
        }
    }
}
