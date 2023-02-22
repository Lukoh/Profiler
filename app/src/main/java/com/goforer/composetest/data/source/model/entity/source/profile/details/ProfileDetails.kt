package com.goforer.composetest.data.source.model.entity.source.profile.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileDetails(
    val nickname: String,
    val birthPlace: String,
    val country: String,
    val cellphone: String
) : Parcelable