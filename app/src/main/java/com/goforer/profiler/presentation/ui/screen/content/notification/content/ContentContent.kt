package com.goforer.profiler.presentation.ui.screen.content.notification.content

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.profiler.presentation.stateholder.business.notification.NotificationViewModel
import com.goforer.profiler.presentation.ui.ext.noRippleClickable

@Composable
fun ContentContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    notificationViewModel: NotificationViewModel,
    userId: Int
) {
    val notification = notificationViewModel.getNotification(userId)

    notification?.let {
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
            TitleItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(4.dp))
            PictureItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(4.dp))
            NameItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(4.dp))
            TeamItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(16.dp))
            ContentItem(modifier = Modifier, it)
        }
    }
}