package com.goforer.profiler.data.repository.notification

import com.goforer.profiler.data.repository.Repository
import com.goforer.profiler.data.source.local.notification.NotificationsLocalDataSource
import com.goforer.profiler.data.source.mediator.LocalDataMediator
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification
import com.goforer.profiler.di.module.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository
@Inject constructor(
    @ApplicationScope private val externalScope: CoroutineScope,
    private val notificationsLocalDataSource: NotificationsLocalDataSource
) : Repository<List<Notification>>() {
    val notifications = object : LocalDataMediator<List<Notification>>(externalScope, replyCount) {
        override fun load() = notificationsLocalDataSource.notifications
    }.asSharedFlow
}

/*
 * Just use the below code if you take data from the Backend server.
 */

/*
import com.goforer.profiler.data.repository.Repository
import com.goforer.profiler.data.source.mediator.DataMediator
import com.goforer.profiler.data.source.model.entity.source.notification.Notification
import com.goforer.profiler.data.source.model.entity.source.response.notification.NotificationResponse
import com.goforer.profiler.data.source.network.api.Params
import com.goforer.profiler.data.source.network.response.Resource
import com.goforer.profiler.di.module.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository
@Inject constructor(
    @ApplicationScope private val externalScope: CoroutineScope
) : Repository<Resource>() {
    override fun trigger(replyCount: Int, params: Params) {
        value = object : DataMediator<NotificationResponse>(externalScope, Companion.replyCount) {
            override fun load() = restAPI.getNotifications(params.args[0] as String)
        }.asSharedFlow
    }
}

 */