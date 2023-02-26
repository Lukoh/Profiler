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

package com.goforer.profiler.presentation.stateholder.business.profile

import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.profile.ProfileRepository
import com.goforer.profiler.data.source.model.entity.source.profile.Profile
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val profileRepository: ProfileRepository,
) : BaseViewModel() {
    private val _profiles = MutableStateFlow<List<Profile>>(listOf())
    val profiles = _profiles

    init {
        viewModelScope.launch {
            profileRepository.profiles.collectLatest {
                _profiles.value = it
            }
        }
    }

    internal fun changeMemberStatus(id: Int, name: String, membered: Boolean) {
        profiles.value.find { it.id == id && it.name == name }?.let {
            it.membered = membered
        }
    }
}

/*
 * Just use the below code if you take data from the Backend server.
 */

/*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.profile.ProfileRepository
import com.goforer.profiler.data.source.model.entity.source.profile.Profile
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import com.goforer.profiler.data.source.network.api.Params
import com.goforer.profiler.data.source.network.response.Resource
import com.goforer.profiler.data.source.network.response.Status
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val profileRepository: ProfileRepository,
) : BaseViewModel() {
    private val _profiles = MutableStateFlow(Resource().loading(Status.LOADING))
    val profiles = _profiles

    init {
        viewModelScope.launch {
            profileRepository.value.collectLatest {
                _profiles.value = it
            }
        }
    }

    override fun request(replyCount: Int, params: Params) {
        profileRepository.request(replyCount = replyCount, params = params)
    }



    internal fun changeMemberStatus(id: Int, name: String, membered: Boolean) {
        @Suppress("UNCHECKED_CAST")
        val profileList = profiles.value as  List<Profile>

        profileList.find { it.id == id && it.name == name }?.let {
            it.membered = membered
        }
    }
}

 */