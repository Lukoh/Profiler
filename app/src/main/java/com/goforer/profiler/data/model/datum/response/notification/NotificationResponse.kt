package com.goforer.profiler.data.model.datum.response.notification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationResponse(
    var data: MutableList<Notification>
) : Parcelable