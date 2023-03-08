package com.goforer.profiler.data.model.datum.response.notification

import android.os.Parcelable
import com.goforer.profiler.data.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationResponse(
    var data: MutableList<Notification>
) : BaseModel(), Parcelable