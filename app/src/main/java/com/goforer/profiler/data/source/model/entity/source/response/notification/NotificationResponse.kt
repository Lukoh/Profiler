package com.goforer.profiler.data.source.model.entity.source.response.notification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationResponse(
    var data: MutableList<Notification>
) : Parcelable