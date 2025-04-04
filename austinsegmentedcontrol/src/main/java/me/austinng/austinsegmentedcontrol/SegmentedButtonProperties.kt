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
class SegmentedButtonProperties constructor(
    val containerBackgroundColor: Color,
    val containerPadding: Dp,
    val containerCornerRadius: Dp,
    val buttonElevation: Dp,
    val buttonShadowColor: Color,
    val buttonBorderWidth: Dp,
    val buttonBorderColor: Color,
    val buttonColor: Color,
    val buttonRadius: Dp,
    val buttonVerticalPadding: Dp,
    val buttonHorizontalPadding: Dp,
    val animationDurationMillis: Int,
    val labelFontSize: TextUnit,
    val labelFontWeight: FontWeight,
    val labelColor: Color,
    val easing: Easing,
) {
    fun copy(
        containerBackgroundColor: Color = this.containerBackgroundColor,
        containerPadding: Dp = this.containerPadding,
        containerCornerRadius: Dp = this.containerCornerRadius,
        buttonElevation: Dp = this.buttonElevation,
        buttonShadowColor: Color = this.buttonShadowColor,
        buttonBorderWidth: Dp = this.buttonBorderWidth,
        buttonBorderColor: Color = this.buttonBorderColor,
        buttonColor: Color = this.buttonColor,
        buttonRadius: Dp = this.buttonRadius,
        buttonVerticalPadding: Dp = this.buttonVerticalPadding,
        buttonHorizontalPadding: Dp = this.buttonHorizontalPadding,
        animationDurationMillis: Int = this.animationDurationMillis,
        labelFontSize: TextUnit = this.labelFontSize,
        labelFontWeight: FontWeight = this.labelFontWeight,
        labelColor: Color = this.labelColor,
        easing: Easing = this.easing,
    ) = SegmentedButtonProperties(
        containerBackgroundColor = containerBackgroundColor.takeOrElse { this.containerBackgroundColor },
        containerPadding = containerPadding.takeOrElse { this.containerPadding },
        containerCornerRadius = containerCornerRadius.takeOrElse { this.containerCornerRadius },
        buttonElevation = buttonElevation.takeOrElse { this.buttonElevation },
        buttonShadowColor = buttonShadowColor.takeOrElse { this.buttonShadowColor },
        buttonBorderWidth = buttonBorderWidth.takeOrElse { this.buttonBorderWidth },
        buttonBorderColor = buttonBorderColor.takeOrElse { this.buttonBorderColor },
        buttonColor = buttonColor.takeOrElse { this.buttonColor },
        buttonRadius = buttonRadius.takeOrElse { this.buttonRadius },
        buttonVerticalPadding = buttonVerticalPadding.takeOrElse { this.buttonVerticalPadding },
        buttonHorizontalPadding = buttonHorizontalPadding.takeOrElse { this.buttonHorizontalPadding },
        animationDurationMillis = animationDurationMillis,
        labelFontSize = labelFontSize.takeOrElse { this.labelFontSize },
        labelFontWeight = labelFontWeight,
        labelColor = labelColor.takeOrElse { this.labelColor },
        easing = easing,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is SegmentedButtonProperties) return false
        if (containerBackgroundColor != other.containerBackgroundColor) return false
        if (containerPadding != other.containerPadding) return false
        if (containerCornerRadius != other.containerCornerRadius) return false
        if (buttonElevation != other.buttonElevation) return false
        if (buttonShadowColor != other.buttonShadowColor) return false
        if (buttonBorderWidth != other.buttonBorderWidth) return false
        if (buttonBorderColor != other.buttonBorderColor) return false
        if (buttonColor != other.buttonColor) return false
        if (buttonRadius != other.buttonRadius) return false
        if (buttonVerticalPadding != other.buttonVerticalPadding) return false
        if (buttonHorizontalPadding != other.buttonHorizontalPadding) return false
        if (animationDurationMillis != other.animationDurationMillis) return false
        if (labelFontSize != other.labelFontSize) return false
        if (labelFontWeight != other.labelFontWeight) return false
        if (labelColor != other.labelColor) return false
        if (easing != other.easing) return false
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = containerBackgroundColor.hashCode()
        result = 31 * result + containerPadding.hashCode()
        result = 31 * result + containerCornerRadius.hashCode()
        result = 31 * result + buttonElevation.hashCode()
        result = 31 * result + buttonShadowColor.hashCode()
        result = 31 * result + buttonBorderWidth.hashCode()
        result = 31 * result + buttonBorderColor.hashCode()
        result = 31 * result + buttonColor.hashCode()
        result = 31 * result + buttonRadius.hashCode()
        result = 31 * result + buttonVerticalPadding.hashCode()
        result = 31 * result + buttonHorizontalPadding.hashCode()
        result = 31 * result + animationDurationMillis.hashCode()
        result = 31 * result + labelFontSize.hashCode()
        result = 31 * result + labelFontWeight.hashCode()
        result = 31 * result + labelColor.hashCode()
        result = 31 * result + easing.hashCode()
        return result
    }
}
