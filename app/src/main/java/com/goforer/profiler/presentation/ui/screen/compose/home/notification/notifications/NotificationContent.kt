/*
 * Copyright (C) 2023 The Android Open Source Project by Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.profiler.presentation.ui.screen.compose.home.notification.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goforer.profiler.data.model.datum.response.notification.Notification
import com.goforer.profiler.data.model.state.ResourceState
import com.goforer.profiler.presentation.stateholder.business.notification.NotificationViewModel

@Composable
fun NotificationContent(
    modifier: Modifier = Modifier,
    notificationViewModel: NotificationViewModel,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    onNavigateToDetailInfo: (Int) -> Unit
) {
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