package com.goforer.profiler.data.model.datum.local.notification

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import com.goforer.profiler.data.model.BaseModel
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PersonItem(
    val sexButtonVisible: Boolean,
    val person: Person,
    val index: Int,
    val followedState: @RawValue MutableState<Boolean>,
) : BaseModel(), Parcelable