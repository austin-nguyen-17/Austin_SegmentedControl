package me.austinng.austinsegmentedcontrol

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

@Composable
internal fun segmentedControlIndicator(
    selectedIndex: Int,
    segmentedControlProperties: SegmentedControlProperties
): @Composable (indicatorPositions: List<IndicatorPosition>) -> Unit = {
    Box(
        modifier = Modifier
            .indicatorOffset(
                it[selectedIndex],
                animationDurationMillis = segmentedControlProperties.animationDurationMillis,
                easing = segmentedControlProperties.easing
            )
            .fillMaxSize()
            .shadow(
                shape = RoundedCornerShape(segmentedControlProperties.indicatorRadius),
                elevation = segmentedControlProperties.indicatorElevation,
                ambientColor = segmentedControlProperties.indicatorShadowColor,
                spotColor = segmentedControlProperties.indicatorShadowColor
            )
            .border(
                segmentedControlProperties.indicatorBorderWidth,
                color = segmentedControlProperties.indicatorBorderColor,
                shape = RoundedCornerShape(segmentedControlProperties.indicatorRadius)
            )
            .background(
                shape = RoundedCornerShape(segmentedControlProperties.indicatorRadius),
                color = segmentedControlProperties.indicatorColor
            )
    )
}