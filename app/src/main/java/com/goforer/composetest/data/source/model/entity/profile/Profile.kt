package com.goforer.composetest.data.source.model.entity.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val id: Int,
    val name: String,
    val sex: String,
    var checked : Boolean = false
) : Parcelable