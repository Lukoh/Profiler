package com.goforer.profiler.presentation.ui.screen.content.notification.notifications

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification

@Composable
fun ListSection(
    modifier: Modifier = Modifier,
    notifications: List<Notification>,
    lazyListState: LazyListState = rememberLazyListState(),
    onItemClicked: (item: Notification, index: Int) -> Unit,
    onNavigateToDetailInfo: (Int) -> Unit
) {
    val showButton by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }
    var clicked by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier,
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(notifications, key = { _, item -> item.id }, itemContent = { index, item ->
                NotificationItem(
                    modifier,
                    item,
                    index,
                    onItemClicked = onItemClicked,
                    onNavigateToDetailInfo = onNavigateToDetailInfo
                )
                //Divider()})
            })
        }

        AnimatedVisibility(
            visible = showButton,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(bottom = 4.dp, end = 8.dp),
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    clicked = true
                }
            ) {
                Text("Up!")
            }
        }

        LaunchedEffect(lazyListState, showButton, clicked) {
            if (showButton && clicked)
                lazyListState.scrollToItem (0)

            clicked = false
        }

        /*
        if (showButton) {
            val coroutineScope = rememberCoroutineScope()

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(bottom = 4.dp, end = 8.dp),
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    coroutineScope.launch {
                        lazyListState.scrollToItem(0)
                    }}
            ) {
                Text("Up!")
            }
        }

         */
    }
}