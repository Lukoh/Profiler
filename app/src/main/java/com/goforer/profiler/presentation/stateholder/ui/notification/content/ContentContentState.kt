package com.goforer.profiler.presentation.stateholder.ui.notification.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.goforer.profiler.data.model.datum.response.notification.Notification
import kotlinx.coroutines.flow.StateFlow

@Stable
class ContentContentState(
    val uiStateFlow: StateFlow<List<Notification>>,
    val onGetNotification: (id: Int) -> Notification?
) {}

@Composable
fun rememberContentContentState(
    uiStateFlow: StateFlow<List<Notification>>,
    onGetNotification: (id: Int) -> Notification?,
): ContentContentState = remember(uiStateFlow, onGetNotification) {
    ContentContentState(uiStateFlow = uiStateFlow, onGetNotification = onGetNotification)
}