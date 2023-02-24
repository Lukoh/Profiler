package com.goforer.profiler.data.source.model.entity.source.response

import android.os.Parcelable
import com.goforer.profiler.data.source.model.entity.source.profile.Profile
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileResponse(
    var data: MutableList<Profile>
) : Parcelable