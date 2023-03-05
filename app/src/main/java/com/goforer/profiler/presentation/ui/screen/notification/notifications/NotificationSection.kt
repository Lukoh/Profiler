package com.goforer.profiler.presentation.ui.screen.notification.notifications

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification
import com.goforer.profiler.presentation.ui.ext.noRippleClickable

@Composable
fun NotificationSection(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    notificationsState: State<List<Notification>>,
    onItemClicked: (item: Notification, index: Int) -> Unit,
    onNavigateToDetailInfo: (Int) -> Unit,
) {
    val lazyListState: LazyListState = rememberLazyListState()

    Box(modifier = modifier) {
        Column(
            modifier = modifier
                .padding(
                    0.dp,
                    contentPadding.calculateTopPadding(),
                    0.dp,
                    0.dp
                )
                .animateContentSize()
                .noRippleClickable { }
        ) {
            ListSection(
                modifier = Modifier.weight(1f),
                notifications = notificationsState.value,
                lazyListState = lazyListState,
                onItemClicked = onItemClicked,
                onNavigateToDetailInfo = onNavigateToDetailInfo
            )
        }
    }
}