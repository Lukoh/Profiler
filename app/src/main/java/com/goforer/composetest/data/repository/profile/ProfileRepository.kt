package com.goforer.composetest.data.repository.profile

import com.goforer.composetest.data.source.local.datasource.ProfilesLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profilesLocalDataSource: ProfilesLocalDataSource
) {
    fun getProfiles(viewModelScope: CoroutineScope) = flow {
        emit(profilesLocalDataSource.profiles)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = listOf()
    )
}