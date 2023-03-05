package com.goforer.profiler.data.source.model.entity.source.response.notification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notification(
    val id: Int,
    val name: String,
    val division: String,
    val team: String,
    val sender: String,
    val title: String,
    val content: String
) : Parcelable