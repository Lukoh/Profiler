package com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.data.model.state.ResourceState
import com.goforer.profiler.data.network.response.Status
import com.goforer.profiler.presentation.stateholder.ui.BaseUiState
import com.goforer.profiler.presentation.stateholder.ui.rememberResourceState
import kotlinx.coroutines.flow.StateFlow

@Stable
class MyNetworkContentState(
    val baseUiState: BaseUiState<List<Person>>,
    val followedState: MutableState<Boolean>,
    var selectedIndex: MutableState<Int>,
    val onFollowStatusChanged: (id: Int, name: String, followed: Boolean) -> Unit,
    val onGetMember: (keyword: String) -> Person?,
    val onDeleteMember: (id: Int) -> Person?,
    val onEstimated: (id: Int, favor: Boolean) -> Person?,
    val resourceState: ResourceState<StateFlow<List<Person>>>
) {
    val resourceStateFlow: StateFlow<List<Person>>? = resourceState.resourceStateFlow
    val status: Status = resourceState.status
}

@Composable
fun rememberMyNetworkContentState(
    baseUiState: BaseUiState<List<Person>>,
    followedState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    selectedIndex: MutableState<Int> = remember { mutableStateOf(-1) },
    resourceState: ResourceState<StateFlow<List<Person>>> = rememberResourceState(resourceStateFlow = baseUiState.resourceStateFlow),
    onFollowStatusChanged: (id: Int, name: String, followed: Boolean) -> Unit,
    onGetMember: (keyword: String) -> Person?,
    onEstimated: (id: Int, favor: Boolean) -> Person?,
    onDeleteMember: (id: Int) -> Person?
): MyNetworkContentState = remember(
    baseUiState, followedState, selectedIndex, resourceState, onFollowStatusChanged,
    onGetMember, onEstimated, onDeleteMember
) {
    MyNetworkContentState(
        baseUiState = baseUiState,
        followedState = followedState,
        selectedIndex = selectedIndex,
        resourceState = resourceState,
        onFollowStatusChanged = onFollowStatusChanged,
        onGetMember = onGetMember,
        onEstimated = onEstimated,
        onDeleteMember = onDeleteMember
    )
}