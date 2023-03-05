package com.goforer.profiler.presentation.ui.screen.notification.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification
import com.goforer.profiler.data.source.model.entity.state.ResourceState
import com.goforer.profiler.presentation.stateholder.business.notification.NotificationViewModel

@Composable
fun NotificationContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    onNavigateToDetailInfo: (Int) -> Unit
) {
    val notificationViewModel: NotificationViewModel = hiltViewModel()
    val notificationsState = notificationViewModel.notifications.collectAsStateWithLifecycle()
    val resourceState by produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (profilesState.resource.status) {
            Status.SUCCESS -> { ResourceState(profilesState.resource.data) }
            Status.ERROR -> { ResourceState(throwError = true) }
            Status.LOADING -> { ResourceState(isLoading = true) }
         */
        value = ResourceState(notificationsState)
    }

    when {
        resourceState.data != null -> {
            @Suppress("UNCHECKED_CAST")
            NotificationSection(
                modifier = modifier, contentPadding,
                onItemClicked = { _, _ ->

                },
                notificationsState = resourceState.data as State<List<Notification>>,
                onNavigateToDetailInfo = onNavigateToDetailInfo
            )
        }
        resourceState.isLoading -> { }
        resourceState.throwError -> { }
    }
}