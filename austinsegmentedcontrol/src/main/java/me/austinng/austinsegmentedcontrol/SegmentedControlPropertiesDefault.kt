package me.austinng.austinsegmentedcontrol

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object SegmentedControlPropertiesDefault {
    @Composable
    fun values() = SegmentedControlProperties(
        offset = 0.dp,
        containerBackgroundColor = Color(0x1F767680),
        containerPadding = 2.dp,
        containerCornerRadius = 9.dp,
        indicatorElevation = 12.dp,
        indicatorShadowColor = Color(0xFF000000),
        indicatorBorderWidth = 0.5.dp,
        indicatorBorderColor = Color(0x0A000000),
        indicatorColor = Color(0xFFFFFFFF),
        indicatorRadius = 7.dp,
        indicatorVerticalPadding = 6.dp,
        indicatorHorizontalPadding = 12.dp,
        animationDurationMillis = 500,
        labelFontSize = 12.sp,
        labelFontWeight = FontWeight.Medium,
        labelColor = Color(0xFF000000),
        easing = FastOutSlowInEasing,
    )
}