package com.goforer.composetest.data.repository.profile

import com.goforer.composetest.data.source.local.datasource.ProfilesLocalDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profilesLocalDataSource: ProfilesLocalDataSource
) {
    val profiles = flow {
        emit(profilesLocalDataSource.profiles)
    }
}