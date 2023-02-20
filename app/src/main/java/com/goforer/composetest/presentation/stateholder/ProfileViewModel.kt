package com.goforer.composetest.presentation.stateholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goforer.composetest.data.source.model.entity.profile.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profiles = MutableStateFlow<List<Profile>>(listOf())
    val profiles = _profiles

    init {
        viewModelScope.launch {
            getProfiles(viewModelScope).collectLatest {
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

private fun getProfiles(viewModelScope: CoroutineScope) = flow {
    emit(mutableListOf(
        Profile(0,"LLyyiok", "남성", true),
        Profile(1,"Alex", "남성", true),
        Profile(2,"Alice", "여성", true),
        Profile(3,"Tana", "여성", true),
        Profile(4,"Lona", "여성", true),
        Profile(5,"Kevin", "남성", true),
        Profile(6,"Jully", "여성", true),
        Profile(7,"Kevin", "남성", true),
        Profile(8,"Tom", "남성", true),
        Profile(9,"Steven", "남성", false),
        Profile(10,"Micle", "남성", false),
        Profile(11,"Micell", "여성", true),
        Profile(12,"Lukoh", "남성", true),
        Profile(13,"Alex", "남성", false),
        Profile(14,"Alice", "여성", false),
        Profile(15,"Tana", "여성", false),
        Profile(16,"Lona", "여성", true),
        Profile(17,"Kevin", "남성", true),
        Profile(18,"Jully", "여성", true),
        Profile(19,"Kevin", "남성", true),
        Profile(20,"Tom", "남성", false),
        Profile(21,"Steven", "남성", false),
        Profile(22,"Micle", "남성", false),
        Profile(23,"Micell", "여성", true),
        Profile(24,"Lukoh", "남성", true),
        Profile(25,"Alex", "남성", false),
        Profile(26,"Alice", "여성", false),
        Profile(27,"Tana", "여성", false),
        Profile(28,"Lona", "여성", true),
        Profile(29,"Kevin", "남성", true),
        Profile(30,"Jully", "여성", true),
        Profile(31,"Kevin", "남성", true),
        Profile(32,"Tom", "남성", false),
        Profile(33,"Steven", "남성", false),
        Profile(34,"Micle", "남성", false),
        Profile(35,"Micell", "여성", true),
    ))
}.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000L),
    initialValue = listOf()
)