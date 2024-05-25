package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.curiosity.R
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData


@Composable
fun CuriosityAreasOfInterest(
    areas: List<CuriosityAreasOfInterestItemData> = emptyList(),
){
    LazyColumn(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9F),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        items(areas.chunked(2)) { pair ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (item in pair){
                    CuriosityAreasOfInterestItem(
                        value = item.value,
                        icon = item.icon,
                        isClicked = item.isClicked,
                        onClick = {
                            item.isClicked = !item.isClicked
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CuriosityAreasOfInterestPreview(){
    val areas: List<CuriosityAreasOfInterestItemData> = listOf(
        CuriosityAreasOfInterestItemData(
            idx = 0,
            value = "SCIENCE",
            icon = R.drawable.science_area,
            isClicked = false,
        ),
        CuriosityAreasOfInterestItemData(
            idx = 1,
            value = "TECHNOLOGY",
            icon = R.drawable.technology_area,
            isClicked = false,
        ),
        CuriosityAreasOfInterestItemData(
            idx = 2,
            value = "SPORT",
            icon = R.drawable.sport_area,
            isClicked = false,
        ),
        CuriosityAreasOfInterestItemData(
            idx = 3,
            value = "FOOD",
            icon = R.drawable.food_area,
            isClicked = false,
        ),
    )

    CuriosityAreasOfInterest(
        areas = areas
    )
}