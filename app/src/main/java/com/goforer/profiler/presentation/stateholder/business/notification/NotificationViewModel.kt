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

package com.goforer.profiler.presentation.stateholder.business.notification

import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.notification.NotificationRepository
import com.goforer.profiler.data.model.datum.response.notification.Notification
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel
@Inject constructor(
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {
    private val _notifications = MutableStateFlow<List<Notification>>(listOf())
    val uiStateFlow: StateFlow<List<Notification>> = _notifications

    init {
        viewModelScope.launch {
            notificationRepository.notifications.collectLatest {
                _notifications.value = it
            }
        }
    }

    internal fun getNotification(id: Int): Notification? {
        uiStateFlow.value.find { it.id == id }?.let {
            return it
        }

        return null
    }
}

/*
 * Just use the below code if you take data from the Backend server.
 */

/*
import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.notification.NotificationRepository
import com.goforer.profiler.data.model.datum.response.notification.Notification
import com.goforer.profiler.data.network.api.Params
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {
    private val _notifications = MutableStateFlow<(listOf())
    val uiState: StateFlow<List<Notification>> = _notifications

    init {
        viewModelScope.launch {
            notificationRepository.value.collectLatest {
                _notifications.value = it
            }
        }
    }

    override fun trigger(replyCount: Int, params: Params) {
        notificationRepository.trigger(replyCount = replyCount, params = params)
    }

    internal fun getNotification(id: Int): Notification? {
        notifications.value.find { it.id == id }?.let {
            return it
        }

        return null
    }
}

 */