package com.goforer.profiler.presentation.stateholder.ui.notification.notifications

import androidx.compose.runtime.*
import com.goforer.profiler.data.model.datum.response.notification.Notification
import com.goforer.profiler.data.model.state.ResourceState
import com.goforer.profiler.data.network.response.Status
import com.goforer.profiler.presentation.stateholder.ui.BaseUiState
import com.goforer.profiler.presentation.stateholder.ui.createResourceState
import kotlinx.coroutines.flow.StateFlow

@Stable
class NotificationContentState(
    val baseUiState: BaseUiState<List<Notification>>,
    resourceState: ResourceState<StateFlow<List<Notification>>>
) {
    val resourceStateFlow: StateFlow<List<Notification>>? = resourceState.resourceStateFlow
    val status: Status = resourceState.status
}

@Composable
fun rememberNotificationContentState(
    baseUiState: BaseUiState<List<Notification>>,
    resourceState: ResourceState<StateFlow<List<Notification>>> = createResourceState(baseUiState)
): NotificationContentState = remember(baseUiState, resourceState) {
    NotificationContentState(baseUiState = baseUiState, resourceState)
}