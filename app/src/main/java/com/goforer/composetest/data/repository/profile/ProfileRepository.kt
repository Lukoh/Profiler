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

package com.goforer.composetest.data.repository.profile

import com.goforer.composetest.data.repository.Repository
import com.goforer.composetest.data.source.local.ProfilesLocalDataSource
import com.goforer.composetest.data.source.mediator.DataMediator
import com.goforer.composetest.data.source.model.entity.source.profile.Profile
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ProfileRepository
@Inject constructor(
    private val profilesLocalDataSource: ProfilesLocalDataSource
) : Repository<List<Profile>>() {
    override fun handle(viewModelScope: CoroutineScope, replyCount: Int) =
        object : DataMediator<List<Profile>>(viewModelScope, replyCount) {
            override fun load() = profilesLocalDataSource.profiles
        }.asSharedFlow
}