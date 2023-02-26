/*
 * Copyright (C) 2021 The Android Open Source Project by Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.profiler.data.repository.profile

import com.goforer.profiler.data.repository.Repository
import com.goforer.profiler.data.source.local.ProfilesLocalDataSource
import com.goforer.profiler.data.source.mediator.LocalDataMediator
import com.goforer.profiler.data.source.model.entity.source.profile.Profile
import com.goforer.profiler.di.module.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository
@Inject constructor(
    @ApplicationScope private val externalScope: CoroutineScope,
    private val profilesLocalDataSource: ProfilesLocalDataSource
) : Repository<List<Profile>>() {
    val profiles = object : LocalDataMediator<List<Profile>>(externalScope, replyCount) {
        override fun load() = profilesLocalDataSource.profiles
    }.asSharedFlow
}

/*
 * Just use the below code if you take data from the Backend server.
 */

/*
import com.goforer.profiler.data.repository.Repository
import com.goforer.profiler.data.source.local.ProfilesLocalDataSource
import com.goforer.profiler.data.source.mediator.DataMediator
import com.goforer.profiler.data.source.mediator.LocalDataMediator
import com.goforer.profiler.data.source.model.entity.source.profile.Profile
import com.goforer.profiler.data.source.model.entity.source.response.ProfileResponse
import com.goforer.profiler.data.source.network.api.Params
import com.goforer.profiler.data.source.network.response.Resource
import com.goforer.profiler.di.module.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository
@Inject constructor(
    @ApplicationScope private val externalScope: CoroutineScope
) : Repository<Resource>() {
    override fun trigger(replyCount: Int, params: Params) {
        profiles = object : DataMediator<ProfileResponse>(externalScope, Companion.replyCount) {
            override fun load() = restAPI.getProfiles(params.args[0] as String)
        }.asSharedFlow
    }
}

 */