/*
 * Copyright (C) 2023 The Android Open Source Project by Lukoh Nam, goForer
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

package com.goforer.profiler.data.repository.mynetwork

import com.goforer.profiler.data.repository.Repository
import com.goforer.profiler.data.source.local.mynetwork.MembersLocalDataSource
import com.goforer.profiler.data.source.mediator.LocalDataMediator
import com.goforer.profiler.data.source.model.entity.source.response.mynetwork.Person
import com.goforer.profiler.di.module.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MembersRepository
@Inject constructor(
    @ApplicationScope private val externalScope: CoroutineScope,
    private val membersLocalDataSource: MembersLocalDataSource
) : Repository<List<Person>>() {
    val profiles = object : LocalDataMediator<List<Person>>(externalScope, replyCount) {
        override fun load() = membersLocalDataSource.members
    }.asSharedFlow
}


/*
 * Just use the below code if you take data from the Backend server.
 */

/*
import com.goforer.profiler.data.repository.Repository
import com.goforer.profiler.data.source.mediator.DataMediator
import com.goforer.profiler.data.source.model.entity.source.response.mynetwork.PersonResponse
import com.goforer.profiler.data.source.network.api.Params
import com.goforer.profiler.data.source.network.response.Resource
import com.goforer.profiler.di.module.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MembersRepository
@Inject constructor(
    @ApplicationScope private val externalScope: CoroutineScope
) : Repository<Resource>() {
    override fun trigger(replyCount: Int, params: Params) {
        value = object : DataMediator<PersonResponse>(externalScope, Companion.replyCount) {
            override fun load() = restAPI.getMembers()
        }.asSharedFlow
    }
}

 */