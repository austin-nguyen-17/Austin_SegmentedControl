package me.austinng.austinsegmentedcontrol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Composable
fun DemoScreen() {
    val scope = rememberCoroutineScope()


    val mainItems = listOf("Home", "Stats", "Chat", "Transaction History").map { SegmentedControlItem(it) }
    var mainIndex by remember { mutableIntStateOf(0) }

    val preferenceItems = listOf("General", "Privacy", "Account").map { SegmentedControlItem(it) }
    var preferenceIndex by remember { mutableIntStateOf(0) }

    val discoveryItems = listOf("Trending", "Search", "Messages", "Alerts", "Profile", "Settings", "Explore", "Bookmarks", "Support", "About").map { SegmentedControlItem(it) }
    var discoveryIndex by remember { mutableIntStateOf(0) }

    val callItems = listOf("All", "Missed").map { SegmentedControlItem(it) }
    val callPagerState = rememberPagerState(pageCount = { callItems.size })
    var callIndex = remember { derivedStateOf { callPagerState.currentPage } }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
            .statusBarsPadding()
    ) {
        Spacer(Modifier.height(32.dp))
        SegmentedControl(
            selectedIndex = mainIndex,
            onItemSelected = {
                mainIndex = it
            },
            items = mainItems,
            itemWidthMode = WidthMode.Proportional,
            segmentedButtonProperties = SegmentedButtonPropertiesDefault.values().copy(offset = 16.dp, buttonHorizontalPadding = 6.dp)
        )
        Spacer(Modifier.height(32.dp))

        SegmentedControl(
            selectedIndex = preferenceIndex,
            onItemSelected = {
                preferenceIndex = it
            },
            items = preferenceItems,
            itemWidthMode = WidthMode.Equal,
            segmentedButtonProperties = SegmentedButtonPropertiesDefault.values().copy(offset = 16.dp)
        )
        Spacer(Modifier.height(32.dp))

        SegmentedControl(
            selectedIndex = discoveryIndex,
            onItemSelected = {
                discoveryIndex = it
            },
            items = discoveryItems,
            itemWidthMode = WidthMode.Proportional,
            segmentedButtonProperties = SegmentedButtonPropertiesDefault.values().copy(offset = 16.dp, buttonHorizontalPadding = 32.dp)
        )
        Spacer(Modifier.height(32.dp))

        AustinShortSegmentedControl(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            selectedIndex = callIndex.value,
            onItemSelected = {
                scope.launch {
                    callPagerState.animateScrollToPage(it)
                }
            },
            items = callItems,
            segmentedButtonProperties = SegmentedButtonPropertiesDefault.values().copy(offset = 16.dp),
        )
        Spacer(Modifier.height(8.dp))

        HorizontalPager(
            state = callPagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .background(color = Color(0x1F767680), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(callItems[page].title, fontSize = 32.sp)
            }
        }
        Spacer(Modifier.height(32.dp))
    }
}