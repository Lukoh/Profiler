package com.goforer.profiler.presentation.stateholder.ui.notification.notifications

import androidx.compose.runtime.*
import com.goforer.profiler.data.model.datum.response.notification.Notification
import com.goforer.profiler.data.model.state.ResourceState
import kotlinx.coroutines.flow.StateFlow

@Stable
class NotificationContentState(
    val uiState: StateFlow<List<Notification>>,
    resourceState: ResourceState<StateFlow<List<Notification>>>
) {
    val data: StateFlow<List<Notification>>? = resourceState.data
    val isLoading: Boolean = resourceState.isLoading
    val throwError: Boolean = resourceState.throwError
}

@Composable
fun rememberNotificationContentState(
    uiState: StateFlow<List<Notification>>,
    resourceState: ResourceState<StateFlow<List<Notification>>> = produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (profilesState.resource.status) {
            Status.SUCCESS -> { ResourceState(profilesState.resource.data) }
            Status.ERROR -> { ResourceState(throwError = true) }
            Status.LOADING -> { ResourceState(isLoading = true) }
        */
        value = ResourceState(uiState)
    }.value
): NotificationContentState = remember(uiState, resourceState) {
    NotificationContentState(uiState, resourceState)
}