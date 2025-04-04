package me.austinng.austinsegmentedcontrol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRow
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


    val homeItems = listOf("Home", "Stats", "Transactions").map { SegmentedControlItem(it) }
    var selectedHomeIndex by remember { mutableIntStateOf(0) }

    val callItems = listOf("All", "Missed").map { SegmentedControlItem(it) }
    val callPagerState = rememberPagerState(pageCount = { callItems.size })
    var selectedCallIndex = remember { derivedStateOf { callPagerState.currentPage } }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        Spacer(Modifier.height(64.dp))
        Text(
            "Equal weight segmented button",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 20.sp
        )
        Spacer(Modifier.height(16.dp))
        SegmentedControl(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            selectedIndex = selectedHomeIndex,
            onItemSelected = {
                selectedHomeIndex = it
            },
            items = homeItems,
            alignItem = SegmentedControlAlignItem.EQUAL_WIDTH
        )
        Spacer(Modifier.height(64.dp))
        Text(
            "Auto align segmented button",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 20.sp
        )
        Spacer(Modifier.height(16.dp))
        SegmentedControl(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            selectedIndex = selectedHomeIndex,
            onItemSelected = {
                selectedHomeIndex = it
            },
            items = homeItems,
            alignItem = SegmentedControlAlignItem.AUTO_ALIGN
        )
        Spacer(Modifier.height(64.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text = "Recent Calls", fontSize = 20.sp)
            Spacer(Modifier.weight(1f))
            SegmentedControl(
                selectedIndex = selectedCallIndex.value,
                onItemSelected = {
                    scope.launch {
                        callPagerState.animateScrollToPage(it)
                    }
                },
                items = callItems
            )
        }
        Spacer(Modifier.height(16.dp))
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
