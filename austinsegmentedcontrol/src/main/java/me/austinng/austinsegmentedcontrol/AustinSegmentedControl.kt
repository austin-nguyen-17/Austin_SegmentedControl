package me.austinng.austinsegmentedcontrol

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastZip

@Composable
fun SegmentedControl(
    modifier: Modifier = Modifier,
    segmentedButtonProperties: SegmentedButtonProperties = SegmentedButtonPropertiesDefault.colors(),
    alignItem: SegmentedControlAlignItem = SegmentedControlAlignItem.EQUAL_WIDTH,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    items: List<SegmentedControlItem>,
) {
    SegmentedControlImpl(
        modifier = modifier
            .background(
                color = segmentedButtonProperties.containerBackgroundColor,
                shape = RoundedCornerShape(segmentedButtonProperties.containerCornerRadius)
            )
            .padding(segmentedButtonProperties.containerPadding),
        segmentedButtonProperties = segmentedButtonProperties,
        alignItemWidth = alignItem,
        selectedIndex = selectedIndex,
        items = items,
        onItemSelected = onItemSelected
    )
}

internal fun Modifier.buttonOffset(
    indicatorPosition: SelectedButtonPosition,
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

@Composable
internal fun SegmentedControlImpl(
    modifier: Modifier = Modifier,
    segmentedButtonProperties: SegmentedButtonProperties,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    alignItemWidth: SegmentedControlAlignItem,
    items: List<SegmentedControlItem>,
) {
    val items: @Composable () -> Unit = {
        items.mapIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .noRippleClickable {
                        onItemSelected(index)
                    }
                    .padding(
                        vertical = segmentedButtonProperties.buttonVerticalPadding,
                        horizontal = segmentedButtonProperties.buttonHorizontalPadding
                    )
            ) {
                BasicText(
                    item.title,
                    style = TextStyle(
                        color = segmentedButtonProperties.labelColor,
                        fontSize = segmentedButtonProperties.labelFontSize,
                        fontWeight = segmentedButtonProperties.labelFontWeight,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
    val indicator: @Composable (indicatorPositions: List<SelectedButtonPosition>) -> Unit = {
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


    SubcomposeLayout(modifier) { constraints ->
        val itemMeasurables = subcompose(ItemsSlot, items)

        val containerHeight = itemMeasurables.maxOf { it.maxIntrinsicHeight(constraints.maxWidth) }
        val labelWidths = itemMeasurables.fastMap {
            it.maxIntrinsicWidth(containerHeight)
        }

        val itemsTotalWidth = labelWidths.sum()
        val containerWidth = maxOf(constraints.minWidth, itemsTotalWidth)

        val itemWidths = when (alignItemWidth) {
            SegmentedControlAlignItem.EQUAL_WIDTH -> {
                List(itemMeasurables.size) {
                    (containerWidth / itemMeasurables.size)
                }
            }

            SegmentedControlAlignItem.AUTO_ALIGN -> {
                val remainingWidth = containerWidth - labelWidths.sum()
                val space = remainingWidth / (labelWidths.size * 2)
                labelWidths.fastMap { textWidth ->
                    textWidth + space * 2
                }
            }
        }


        val itemPlaceables = itemMeasurables.fastZip(itemWidths) { measurable, itemWidth ->
            measurable.measure(
                Constraints.fixed(itemWidth, containerHeight)
            )
        }

        val lefts = listOf(0) + itemPlaceables.map { it.width }.runningReduce { sum, left ->
            sum + left
        }.dropLast(1)
        val buttonPositions = itemPlaceables.zip(lefts) { item, left ->
            SelectedButtonPosition(left.toDp(), item.width.toDp())
        }

        layout(containerWidth, containerHeight) {
            subcompose(ButtonSlot) {
                indicator(buttonPositions)
            }.fastMap {
                it.measure(Constraints.fixed(containerWidth, containerHeight))
                    .place(0, 0)
            }

            itemPlaceables.fastZip(lefts) { itemPlaceable, left ->
                itemPlaceable.place(left, 0)
            }
        }
    }
}

private object ItemsSlot
private object ButtonSlot

@Immutable
internal data class SelectedButtonPosition(
    val left: Dp,
    val width: Dp
)