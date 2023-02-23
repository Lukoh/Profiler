package com.goforer.composetest.presentation.stateholder.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goforer.composetest.data.repository.profile.ProfileRepository
import com.goforer.composetest.data.source.model.entity.source.profile.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _profiles = MutableStateFlow<List<Profile>>(listOf())
    val profiles = _profiles

    init {
        viewModelScope.launch {
            profileRepository.profiles.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = listOf()
            ).collectLatest {
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