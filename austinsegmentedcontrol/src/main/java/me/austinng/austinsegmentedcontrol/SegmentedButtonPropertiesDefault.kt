package me.austinng.austinsegmentedcontrol

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object SegmentedButtonPropertiesDefault {
    @Composable
    fun colors() = SegmentedButtonProperties(
        containerBackgroundColor = Color(0x1F767680),
        containerPadding = 2.dp,
        containerCornerRadius = 9.dp,
        buttonElevation = 12.dp,
        buttonShadowColor = Color(0xFF000000),
        buttonBorderWidth = 0.5.dp,
        buttonBorderColor = Color(0x0A000000),
        buttonColor = Color(0xFFFFFFFF),
        buttonRadius = 7.dp,
        buttonVerticalPadding = 6.dp,
        buttonHorizontalPadding = 12.dp,
        animationDurationMillis = 500,
        labelFontSize = 12.sp,
        labelFontWeight = FontWeight.Medium,
        labelColor = Color(0xFF000000),
        easing = FastOutSlowInEasing,
    )
}