package com.goforer.profiler.presentation.stateholder.ui.notification.notifications

import androidx.compose.runtime.*
import com.goforer.profiler.data.model.datum.response.notification.Notification
import com.goforer.profiler.data.model.state.ResourceState
import com.goforer.profiler.presentation.stateholder.ui.BaseUiState
import kotlinx.coroutines.flow.StateFlow

@Stable
class NotificationContentState(
    val baseUiState: BaseUiState<List<Notification>>,
    resourceState: ResourceState<StateFlow<List<Notification>>>
) {
    val data: StateFlow<List<Notification>>? = resourceState.data
    val isSuccess: Boolean = resourceState.isSuccess
    val isLoading: Boolean = resourceState.isLoading
    val throwError: Boolean = resourceState.throwError
}

@Composable
fun rememberNotificationContentState(
    baseUiState: BaseUiState<List<Notification>>,
    resourceState: ResourceState<StateFlow<List<Notification>>> = produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (baseUiState.uiState.resource.status) {
            Status.SUCCESS -> { ResourceState(isSuccess = true, data = baseUiState.uiState.resource.data) }
            Status.ERROR -> { ResourceState(throwError = true) }
            Status.LOADING -> { ResourceState(isLoading = true) }
        */
        value = ResourceState(isSuccess = true, data = baseUiState.uiState)
    }.value
): NotificationContentState = remember(baseUiState, resourceState) {
    NotificationContentState(baseUiState = baseUiState, resourceState)
}