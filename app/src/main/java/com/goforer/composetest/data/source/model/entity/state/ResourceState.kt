package com.goforer.composetest.data.source.model.entity.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ResourceState(
    val data: @RawValue Any? = null,
    val isLoading: Boolean = false,
    val throwError: Boolean = false
) : Parcelable