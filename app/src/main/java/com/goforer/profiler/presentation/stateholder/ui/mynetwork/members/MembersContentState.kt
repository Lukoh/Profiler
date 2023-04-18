package com.goforer.profiler.presentation.stateholder.ui.mynetwork.members

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.data.model.state.ResourceState
import com.goforer.profiler.data.network.response.Status
import com.goforer.profiler.presentation.stateholder.ui.BaseUiState
import com.goforer.profiler.presentation.stateholder.ui.rememberResourceState
import kotlinx.coroutines.flow.StateFlow

@Stable
class MembersContentState(
    val baseUiState: BaseUiState<List<Person>>,
    val sexState: MutableState<String>,
    val followedState: MutableState<Boolean>,
    val onGetMembers: (sex: String) -> List<Person>,
    val onGetMember: (sex: String) -> Person?,
    val onEstimated: (id: Int, favor: Boolean) -> Person?,
    val resourceState: ResourceState<StateFlow<List<Person>>>
) {
    val resourceStateFlow: StateFlow<List<Person>>? = resourceState.resourceStateFlow
    val status: Status = resourceState.status

    lateinit var membersState: State<List<Person>>
}

@Composable
fun rememberMembersContentState(
    baseUiState: BaseUiState<List<Person>>,
    sexState: MutableState<String> = rememberSaveable { mutableStateOf("") },
    followedState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    onGetMembers: (sex: String) -> List<Person>,
    onGetMember: (sex: String) -> Person?,
    onEstimated: (id: Int, favor: Boolean) -> Person?,
    resourceState: ResourceState<StateFlow<List<Person>>> = rememberResourceState(resourceStateFlow = baseUiState.resourceStateFlow)
): MembersContentState = remember(baseUiState, sexState, followedState, onGetMembers, onGetMember, onEstimated, resourceState) {
    MembersContentState(
        baseUiState = baseUiState,
        sexState = sexState,
        followedState = followedState,
        onGetMembers = onGetMembers,
        onGetMember = onGetMember,
        onEstimated = onEstimated,
        resourceState = resourceState
    )
}