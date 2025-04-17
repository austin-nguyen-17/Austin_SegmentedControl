package me.austinng.austinsegmentedcontrol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastZip

@Composable
fun AustinShortSegmentedControl(
    modifier: Modifier = Modifier,
    segmentedControlProperties: SegmentedControlProperties = SegmentedControlPropertiesDefault.values(),
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    items: List<SegmentedControlItem>,
) {
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
            .padding(segmentedControlProperties.offset)
    ) {
        ShortSegmentedControlContainer(
            color = segmentedControlProperties.containerBackgroundColor,
            radius = segmentedControlProperties.containerCornerRadius,
            padding = segmentedControlProperties.containerPadding,
            items = itemUis,
            indicator = indicator,
        )
    }
}


@Composable
private fun ShortSegmentedControlContainer(
    color: Color,
    radius: Dp,
    padding: Dp,
    items: @Composable () -> Unit,
    indicator: @Composable (indicatorPositions: List<IndicatorPosition>) -> Unit,
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

        val containerHeight =
            itemMeasurables.maxOf { it.maxIntrinsicHeight(Constraints.Infinity) }
        val itemWidths = itemMeasurables.fastMap {
            it.maxIntrinsicWidth(containerHeight)
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