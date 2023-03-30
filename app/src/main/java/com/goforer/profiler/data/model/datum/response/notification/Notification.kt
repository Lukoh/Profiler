package com.goforer.profiler.data.model.datum.response.notification

import android.os.Parcelable
import com.goforer.profiler.data.model.BaseModel
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notification(
    val id: Int,
    val name: String,
    val division: String,
    val team: String,
    val sender: Person,
    val title: String,
    val importance: Int,
    val content: String
) : BaseModel(), Parcelable