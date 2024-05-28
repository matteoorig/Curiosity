package com.curiosity.presentation.home.content.composable

/**
 * @author matteooriggi
 */

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * The source code of the animations comes from https://www.sinasamaki.com/shake-animations-compose/
 */
class ShakeController {
    var shakeConfig: ShakeConfig? by mutableStateOf(null)
        private set

    fun shake(shakeConfig: ShakeConfig) {
        this.shakeConfig = shakeConfig
    }
}