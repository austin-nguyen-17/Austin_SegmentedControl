package me.austinng.austinsegmentedcontrol

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
sealed class ItemWidthMode {
    @Stable
    object Equal: ItemWidthMode()
    @Stable
    object Proportional: ItemWidthMode()
}
