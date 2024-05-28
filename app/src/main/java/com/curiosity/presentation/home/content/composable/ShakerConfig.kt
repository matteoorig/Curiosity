package com.curiosity.presentation.home.content.composable

/**
 * @author matteooriggi
 */

/**
 * The source code of the animations comes from https://www.sinasamaki.com/shake-animations-compose/
 */

data class ShakeConfig(
    val iterations: Int,
    val intensity: Float = 100_000f,
    val rotate: Float = 0f,
    val rotateX: Float = 0f,
    val rotateY: Float = 0f,
    val scaleX: Float = 0f,
    val scaleY: Float = 0f,
    val translateX: Float = 0f,
    val translateY: Float = 0f,
    val trigger: Long = System.currentTimeMillis(),
)
