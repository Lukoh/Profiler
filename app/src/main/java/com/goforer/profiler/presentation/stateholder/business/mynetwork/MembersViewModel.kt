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
import com.goforer.profiler.data.repository.mynetwork.MembersRepository
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembersViewModel
@Inject constructor(
    private val membersRepository: MembersRepository
) : BaseViewModel() {
    private val _members = MutableStateFlow<List<Person>>(listOf())
    val resourceStateFlow: StateFlow<List<Person>> = _members

    init {
        viewModelScope.launch {
            membersRepository.profiles.collectLatest {
                _members.value = it
            }
        }
    }

    internal fun getMembers(sex: String): List<Person> {
        return resourceStateFlow.value.filter { it.sex == sex }
    }

    internal fun getMember(sex: String): Person? {
        return resourceStateFlow.value.find { it.sex == sex }
    }

    internal fun setEstimation(id: Int, favor: Boolean): Person? {
        resourceStateFlow.value.find { it.id == id }?.let {
            it.favor = favor

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
import com.goforer.profiler.data.repository.mynetwork.MembersRepository
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.business.BaseViewModel
import com.goforer.profiler.data.network.api.Params
import com.goforer.profiler.data.network.response.Resource
import com.goforer.profiler.data.network.response.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val membersRepository: MembersRepository
) : BaseViewModel() {
    private val _members = MutableStateFlow(Resource().loading(Status.LOADING))
    val uiState = _members

    init {
        viewModelScope.launch {
            membersRepository.value.collectLatest {
                _members.value = it
            }
        }
    }

    override fun trigger(replyCount: Int, params: Params) {
        membersRepository.trigger(replyCount = replyCount, params = params)
    }
}

 */