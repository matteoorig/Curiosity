package com.curiosity.domain.model

/**
 * @author matteooriggi
 */

/**
 * Data class representing an item in the areas of interest.
 *
 * This class is used to model an item in the list of areas of interest,
 * including its index, value, icon, and whether it has been clicked.
 *
 * @param idx The index of the item.
 * @param value The string value representing the name or description of the item.
 * @param icon The resource ID of the icon associated with the item.
 * @param isClicked A boolean flag indicating whether the item has been clicked. Defaults to false.
 */
data class CuriosityAreasOfInterestItemData(
    val idx: Int,
    val value: String,
    val icon: Int,
    var isClicked: Boolean = false
)
