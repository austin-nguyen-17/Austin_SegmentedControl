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
    segmentedButtonProperties: SegmentedButtonProperties
): @Composable (indicatorPositions: List<ButtonPosition>) -> Unit = {
    Box(
        modifier = Modifier
            .buttonOffset(
                it[selectedIndex],
                animationDurationMillis = segmentedButtonProperties.animationDurationMillis,
                easing = segmentedButtonProperties.easing
            )
            .fillMaxSize()
            .shadow(
                shape = RoundedCornerShape(segmentedButtonProperties.buttonRadius),
                elevation = segmentedButtonProperties.buttonElevation,
                ambientColor = segmentedButtonProperties.buttonShadowColor,
                spotColor = segmentedButtonProperties.buttonShadowColor
            )
            .border(
                segmentedButtonProperties.buttonBorderWidth,
                color = segmentedButtonProperties.buttonBorderColor,
                shape = RoundedCornerShape(segmentedButtonProperties.buttonRadius)
            )
            .background(
                shape = RoundedCornerShape(segmentedButtonProperties.buttonRadius),
                color = segmentedButtonProperties.buttonColor
            )
    )
}