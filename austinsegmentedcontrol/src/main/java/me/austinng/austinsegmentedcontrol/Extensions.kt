package me.austinng.austinsegmentedcontrol

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp

internal fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

internal fun Modifier.indicatorOffset(
    indicatorPosition: IndicatorPosition,
    animationDurationMillis: Int,
    easing: Easing
): Modifier =
    composed {
        val indicatorWidth by animateDpAsState(
            indicatorPosition.width,
            animationSpec = tween(durationMillis = animationDurationMillis, easing = easing)
        )
        val indicatorOffset by animateDpAsState(
            indicatorPosition.left,
            animationSpec = tween(durationMillis = animationDurationMillis, easing = easing)
        )
        fillMaxWidth()
            .wrapContentSize(Alignment.BottomStart)
            .offset(x = indicatorOffset)
            .width(indicatorWidth)
    }


internal object ItemsSlot
internal object IndicatorSlot

@Immutable
internal data class IndicatorPosition(
    val left: Dp,
    val width: Dp
)

internal enum class Allocation {
    EQUAL,
    WRAP,
    PROPORTION
}