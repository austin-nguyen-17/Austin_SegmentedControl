package me.austinng.austinsegmentedcontrol

import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.takeOrElse


@Immutable
class SegmentedControlProperties constructor(
    val offset: Dp,
    val containerBackgroundColor: Color,
    val containerPadding: Dp,
    val containerCornerRadius: Dp,
    val indicatorElevation: Dp,
    val indicatorShadowColor: Color,
    val indicatorBorderWidth: Dp,
    val indicatorBorderColor: Color,
    val indicatorColor: Color,
    val indicatorRadius: Dp,
    val indicatorVerticalPadding: Dp,
    val indicatorHorizontalPadding: Dp,
    val animationDurationMillis: Int,
    val labelFontSize: TextUnit,
    val labelFontWeight: FontWeight,
    val labelColor: Color,
    val easing: Easing,
) {
    fun copy(
        offset: Dp = this.offset,
        containerBackgroundColor: Color = this.containerBackgroundColor,
        containerPadding: Dp = this.containerPadding,
        containerCornerRadius: Dp = this.containerCornerRadius,
        indicatorElevation: Dp = this.indicatorElevation,
        indicatorShadowColor: Color = this.indicatorShadowColor,
        indicatorBorderWidth: Dp = this.indicatorBorderWidth,
        indicatorBorderColor: Color = this.indicatorBorderColor,
        indicatorColor: Color = this.indicatorColor,
        indicatorRadius: Dp = this.indicatorRadius,
        indicatorVerticalPadding: Dp = this.indicatorVerticalPadding,
        indicatorHorizontalPadding: Dp = this.indicatorHorizontalPadding,
        animationDurationMillis: Int = this.animationDurationMillis,
        labelFontSize: TextUnit = this.labelFontSize,
        labelFontWeight: FontWeight = this.labelFontWeight,
        labelColor: Color = this.labelColor,
        easing: Easing = this.easing,
    ) = SegmentedControlProperties(
        offset = offset.takeOrElse { this.offset },
        containerBackgroundColor = containerBackgroundColor.takeOrElse { this.containerBackgroundColor },
        containerPadding = containerPadding.takeOrElse { this.containerPadding },
        containerCornerRadius = containerCornerRadius.takeOrElse { this.containerCornerRadius },
        indicatorElevation = indicatorElevation.takeOrElse { this.indicatorElevation },
        indicatorShadowColor = indicatorShadowColor.takeOrElse { this.indicatorShadowColor },
        indicatorBorderWidth = indicatorBorderWidth.takeOrElse { this.indicatorBorderWidth },
        indicatorBorderColor = indicatorBorderColor.takeOrElse { this.indicatorBorderColor },
        indicatorColor = indicatorColor.takeOrElse { this.indicatorColor },
        indicatorRadius = indicatorRadius.takeOrElse { this.indicatorRadius },
        indicatorVerticalPadding = indicatorVerticalPadding.takeOrElse { this.indicatorVerticalPadding },
        indicatorHorizontalPadding = indicatorHorizontalPadding.takeOrElse { this.indicatorHorizontalPadding },
        animationDurationMillis = animationDurationMillis,
        labelFontSize = labelFontSize.takeOrElse { this.labelFontSize },
        labelFontWeight = labelFontWeight,
        labelColor = labelColor.takeOrElse { this.labelColor },
        easing = easing,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is SegmentedControlProperties) return false
        if (offset != other.offset) return false
        if (containerBackgroundColor != other.containerBackgroundColor) return false
        if (containerPadding != other.containerPadding) return false
        if (containerCornerRadius != other.containerCornerRadius) return false
        if (indicatorElevation != other.indicatorElevation) return false
        if (indicatorShadowColor != other.indicatorShadowColor) return false
        if (indicatorBorderWidth != other.indicatorBorderWidth) return false
        if (indicatorBorderColor != other.indicatorBorderColor) return false
        if (indicatorColor != other.indicatorColor) return false
        if (indicatorRadius != other.indicatorRadius) return false
        if (indicatorVerticalPadding != other.indicatorVerticalPadding) return false
        if (indicatorHorizontalPadding != other.indicatorHorizontalPadding) return false
        if (animationDurationMillis != other.animationDurationMillis) return false
        if (labelFontSize != other.labelFontSize) return false
        if (labelFontWeight != other.labelFontWeight) return false
        if (labelColor != other.labelColor) return false
        if (easing != other.easing) return false
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = containerBackgroundColor.hashCode()
        result = 31 * result + offset.hashCode()
        result = 31 * result + containerPadding.hashCode()
        result = 31 * result + containerCornerRadius.hashCode()
        result = 31 * result + indicatorElevation.hashCode()
        result = 31 * result + indicatorShadowColor.hashCode()
        result = 31 * result + indicatorBorderWidth.hashCode()
        result = 31 * result + indicatorBorderColor.hashCode()
        result = 31 * result + indicatorColor.hashCode()
        result = 31 * result + indicatorRadius.hashCode()
        result = 31 * result + indicatorVerticalPadding.hashCode()
        result = 31 * result + indicatorHorizontalPadding.hashCode()
        result = 31 * result + animationDurationMillis.hashCode()
        result = 31 * result + labelFontSize.hashCode()
        result = 31 * result + labelFontWeight.hashCode()
        result = 31 * result + labelColor.hashCode()
        result = 31 * result + easing.hashCode()
        return result
    }
}
