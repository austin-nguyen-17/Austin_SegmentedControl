package me.austinng.austinsegmentedcontrol

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

data class SegmentedControlItem(val title: String)

@Composable
internal fun SegmentedControlItemUi(
    item: SegmentedControlItem,
    properties: SegmentedControlProperties,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .noRippleClickable {
                onClick()
            }
            .padding(
                vertical = properties.indicatorVerticalPadding,
                horizontal = properties.indicatorHorizontalPadding
            )
    ) {
        BasicText(
            item.title,
            style = TextStyle(
                color = properties.labelColor,
                fontSize = properties.labelFontSize,
                fontWeight = properties.labelFontWeight,
                textAlign = TextAlign.Center
            )
        )
    }

}
