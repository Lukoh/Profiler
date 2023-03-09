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
import com.goforer.profiler.presentation.stateholder.ui.notification.notifications.NotificationContentState
import timber.log.Timber

@Composable
fun NotificationContent(
    modifier: Modifier = Modifier,
    state: NotificationContentState,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    onNavigateToDetailInfo: (Int) -> Unit
) {
    val uiState = state.uiState.collectAsStateWithLifecycle()

    when {
        state.data != null -> {
            NotificationSection(
                modifier = modifier, contentPadding,
                onItemClicked = { _, _ ->

                },
                state = state.data.collectAsStateWithLifecycle(),
                onNavigateToDetailInfo = onNavigateToDetailInfo
            )
        }
        state.isLoading -> {}
        state.throwError -> {}
    }

    Timber.d("size: %d", uiState.value.size)
}