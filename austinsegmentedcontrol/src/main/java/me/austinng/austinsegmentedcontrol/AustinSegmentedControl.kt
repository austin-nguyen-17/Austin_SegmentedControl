package me.austinng.austinsegmentedcontrol

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastZip
import kotlin.math.roundToInt

@Composable
fun SegmentedControl(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    segmentedControlProperties: SegmentedControlProperties,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    itemWidthMode: ItemWidthMode,
    items: List<SegmentedControlItem>,
) {
    var centers by remember { mutableStateOf(listOf<Int>()) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val scrollOffset = with(LocalDensity.current) {
        val halfScreen = screenWidth.toPx() / 2
        val leftPadding = segmentedControlProperties.offset.toPx() + segmentedControlProperties.containerPadding.toPx()
        (halfScreen - leftPadding).roundToInt()
    }

    LaunchedEffect(selectedIndex) {
        val target = centers[selectedIndex] - scrollOffset
        scrollState.animateScrollTo(
            target,
            animationSpec = tween(
                durationMillis = segmentedControlProperties.animationDurationMillis,
                easing = segmentedControlProperties.easing
            )
        )
    }

    val itemUis: @Composable () -> Unit = {
        items.mapIndexed { index, item ->
            SegmentedControlItemUi(
                item = item,
                properties = segmentedControlProperties,
                onClick = { onItemSelected(index) }
            )
        }
    }

    val indicator: @Composable (indicatorPositions: List<IndicatorPosition>) -> Unit =
        segmentedControlIndicator(selectedIndex, segmentedControlProperties)

    Row(
        modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(segmentedControlProperties.offset)
    ) {
        SegmentedControlContainer(
            color = segmentedControlProperties.containerBackgroundColor,
            radius = segmentedControlProperties.containerCornerRadius,
            padding = segmentedControlProperties.containerPadding,
            screenWidthExcludePadding = screenWidth - segmentedControlProperties.offset * 2 - segmentedControlProperties.containerPadding * 2,
            itemWidthMode = itemWidthMode,
            items = itemUis,
            indicator = indicator,
            onItemCentersCalculated = {
                centers = it
            }
        )
    }
}



@Composable
private fun SegmentedControlContainer(
    color: Color,
    radius: Dp,
    padding: Dp,
    screenWidthExcludePadding: Dp,
    itemWidthMode: ItemWidthMode? = null,
    items: @Composable () -> Unit,
    indicator: @Composable (indicatorPositions: List<IndicatorPosition>) -> Unit,
    onItemCentersCalculated: (List<Int>) -> Unit,
) {
    SubcomposeLayout(
        Modifier
            .background(
                color = color,
                shape = RoundedCornerShape(radius)
            )
            .padding(padding)
    ) { constraints ->
        val itemMeasurables = subcompose(ItemsSlot, items)

        val containerHeight = itemMeasurables.maxOf { it.maxIntrinsicHeight(Constraints.Infinity) }
        val labelWidths = itemMeasurables.fastMap { it.maxIntrinsicWidth(containerHeight) }
        val availableWidthForItems = screenWidthExcludePadding.roundToPx()

        val allocation = when {
            availableWidthForItems < labelWidths.sum() -> Allocation.WRAP
            itemWidthMode == ItemWidthMode.Equal -> Allocation.EQUAL
            else -> Allocation.PROPORTION
        }

        val itemWidths = when (allocation) {
            Allocation.EQUAL -> {
                List(itemMeasurables.size) {
                    (availableWidthForItems / itemMeasurables.size)
                }
            }

            Allocation.PROPORTION -> {
                val remainingWidth = availableWidthForItems - labelWidths.sum()
                val space = remainingWidth / (labelWidths.size * 2)
                labelWidths.fastMap { textWidth ->
                    textWidth + space * 2
                }
            }

            Allocation.WRAP -> {
                labelWidths
            }
        }
        val itemTotalWidth = itemWidths.sum()


        val itemPlaceables = itemMeasurables.fastZip(itemWidths) { measurable, itemWidth ->
            measurable.measure(
                Constraints.fixed(itemWidth, containerHeight)
            )
        }

        val lefts = itemWidths.runningFold(0) { acc, w -> acc + w }.dropLast(1)

        val indicatorPositions = itemPlaceables.fastZip(lefts) { item, left ->
            IndicatorPosition(left.toDp(), item.width.toDp())
        }

        val centers = itemWidths.fastZip(lefts) { w, l -> l + w / 2 }
        onItemCentersCalculated(centers)

        layout(itemTotalWidth, containerHeight) {
            subcompose(IndicatorSlot) {
                indicator(indicatorPositions)
            }.fastMap {
                it.measure(Constraints.fixed(itemTotalWidth, containerHeight)).place(0, 0)
            }

            itemPlaceables.fastZip(lefts) { itemPlaceable, left ->
                itemPlaceable.place(left, 0)
            }
        }
    }
}