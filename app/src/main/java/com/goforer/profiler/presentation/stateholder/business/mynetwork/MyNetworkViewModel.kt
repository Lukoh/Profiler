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

package com.goforer.profiler.presentation.stateholder.business.mynetwork

import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.mynetwork.MyNetworkRepository
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyNetworkViewModel
@Inject constructor(
    private val myNetworkRepository: MyNetworkRepository
) : BaseViewModel() {
    private val _myNetworks = MutableStateFlow<List<Person>>(listOf())
    val resourceStateFlow: StateFlow<List<Person>> = _myNetworks

    init {
        viewModelScope.launch {
            try {
                myNetworkRepository.profiles.collectLatest {
                    _myNetworks.value = it
                }
            } catch(e: Exception) {
                _myNetworks.value = emptyList()
            }

        }
    }

    internal fun changeFollowStatus(id: Int, name: String, followed: Boolean) {
        resourceStateFlow.value.find { it.id == id && it.name == name }?.let {
            it.followed = followed
        }
    }

    internal fun getMember(id: Int): Person? {
        resourceStateFlow.value.find { it.id == id }?.let {
            return it
        }

        return null
    }

    internal fun getMember(keyword: String): Person? {
        resourceStateFlow.value.find { it.name.contains(keyword) }?.let {
            return it
        }

        return null
    }

    internal fun setEstimation(id: Int, favor: Boolean): Person? {
        resourceStateFlow.value.find { it.id == id }?.let {
            it.favor = favor

            return it
        }

        return null
    }

    internal fun deleteMember(id: Int): Person? {
        resourceStateFlow.value.find { it.id == id }?.let {
            it.deleted = true

            return it
        }

        return null
    }
}

/*
 * Just use the below code if you take data from the Backend server.
 */

/*
import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.mynetwork.MyNetworkRepository
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.data.network.api.Params
import com.goforer.profiler.data.network.response.Resource
import com.goforer.profiler.data.network.response.Status
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val myNetworkRepository: MyNetworkRepository
) : BaseViewModel() {
    private val _myNetworks = MutableStateFlow(Resource().loading(Status.LOADING))
    val uiState = _myNetworks

    init {
        viewModelScope.launch {
            myNetworkRepository.value.collectLatest {
                _myNetworks.value = it
            }
        }
    }

    override fun trigger(replyCount: Int, params: Params) {
        myNetworkRepository.trigger(replyCount = replyCount, params = params)
    }

    internal fun changeFollowStatus(id: Int, name: String, followed: Boolean) {
        val profileList = myNetworks.value as List<Person>

        profileList.find { it.id == id && it.name == name }?.let {
            it.followed = followed
        }
    }

    internal fun getPerson(id: Int): Person? {
        myNetworks.value.data?.find { it.id == id }?.let {
            return it
        }

        return null
    }
}

 */