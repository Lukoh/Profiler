package com.goforer.profiler.presentation.stateholder.business.notification

import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.notification.NotificationRepository
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification
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
    val notifications: StateFlow<List<Notification>> = _notifications

    init {
        viewModelScope.launch {
            notificationRepository.notifications.collectLatest {
                _notifications.value = it
            }
        }
    }

    internal fun getNotification(id: Int): Notification? {
        notifications.value.find { it.id == id }?.let {
            return it
        }

        return null
    }
}

/*
 * Just use the below code if you take data from the Backend server.
 */

/*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.notification.NotificationRepository
import com.goforer.profiler.data.source.model.entity.source.nofication.Notification
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import com.goforer.profiler.data.source.network.api.Params
import com.goforer.profiler.data.source.network.response.Resource
import com.goforer.profiler.data.source.network.response.Status
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {
    private val _notifications = MutableStateFlow<List<Notification>>(listOf())
    val notifications: StateFlow<List<Notification>> = _notifications

    init {
        viewModelScope.launch {
            notificationRepository.value.collectLatest {
                _notifications.value = it
            }
        }
    }

    override fun trigger(replyCount: Int, params: Params) {
        notificationRepository.request(replyCount = replyCount, params = params)
    }

    internal fun getNotification(id: Int): Notification? {
        notifications.value.find { it.id == id }?.let {
            return it
        }

        return null
    }
}

 */