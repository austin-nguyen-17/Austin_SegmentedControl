package me.austinng.austinsegmentedcontrol

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
sealed class WidthMode {
    @Stable
    object Equal: WidthMode()
    @Stable
    object Proportional: WidthMode()
}
