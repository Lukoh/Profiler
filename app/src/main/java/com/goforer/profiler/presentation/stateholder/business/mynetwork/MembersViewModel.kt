package com.goforer.profiler.presentation.stateholder.business.mynetwork

import androidx.lifecycle.viewModelScope
import com.goforer.profiler.data.repository.mynetwork.MembersRepository
import com.goforer.profiler.data.source.model.entity.source.response.mynetwork.Person
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
    val members: StateFlow<List<Person>> = _members

    init {
        viewModelScope.launch {
            membersRepository.profiles.collectLatest {
                _members.value = it
            }
        }
    }
}