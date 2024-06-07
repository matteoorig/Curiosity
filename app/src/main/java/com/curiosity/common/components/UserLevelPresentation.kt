package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.R
import com.curiosity.domain.model.Badge
import com.curiosity.ui.theme.CuriosityViolet

fun BadgeShape(): GenericShape {
    return GenericShape { size, _ ->
        val width = size.width
        val height = size.height

        moveTo(width * 0.5f, height * 0.95f)
        lineTo(width * 0.1f, height * 0.67f)
        lineTo(width * 0.1f, height * 0.2f)
        lineTo(width * 0.5f, height * 0.05f)
        lineTo(width * 0.9f, height * 0.2f)
        lineTo(width * 0.9f, height * 0.67f)
        close()
    }
}

@Composable
fun LevelBadge(
    drawableResource: Int,
    isLocked: Boolean,
) {
    var isEnable by remember { mutableStateOf(isLocked) }

    Box(modifier = Modifier){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(150.dp)
                .width(120.dp)
                .padding(16.dp)
                .animateContentSize()
        ) {
            CuriositySvg(
                modifier = Modifier.size(100.dp),
                drawableResource = drawableResource
            )

        }
        if (isEnable) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(120.dp)
                    .clip(BadgeShape())
                    .background(CuriosityViolet.copy(alpha = 0.7f))
            )
        }
        if (isEnable) {

            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(120.dp),
                contentAlignment = Alignment.Center,
            ){
                CuriosityText(
                    modifier = Modifier,
                    value = "100 trophy",
                    textColor = Color.White,
                    textSize = 16.sp,
                    textWeight = FontWeight.Bold
                )
            }

        }
    }
}

@Preview
@Composable
fun LevelBadgePreview(){
    LevelBadge(
        drawableResource = R.drawable.badge_1,
        isLocked = true
    )
}

@Composable
fun UserLevelPresentation(
    badges: List<Badge>
) {
    LazyRow {
        items(badges){badge ->
            LevelBadge(
                drawableResource = badge.drawableResource,
                isLocked = badge.isLocked
            )
        }
    }
}

@Preview
@Composable
fun UserLevelPresentationPreview(){
    val badges: List<Badge> = listOf(
        Badge(
            drawableResource = R.drawable.badge_1,
            isLocked = false
        ),
        Badge(
            drawableResource = R.drawable.badge_2,
            isLocked = true
        ),
        Badge(
            drawableResource = R.drawable.badge_3,
            isLocked = true
        ),
        Badge(
            drawableResource = R.drawable.badge_4,
            isLocked = true
        ),
        Badge(
            drawableResource = R.drawable.badge_5,
            isLocked = true
        ),
        Badge(
            drawableResource = R.drawable.badge_6,
            isLocked = true
        )
    )
    UserLevelPresentation(
        badges = badges
    )
}

