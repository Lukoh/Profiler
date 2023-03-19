package com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.data.model.state.ResourceState
import com.goforer.profiler.presentation.stateholder.ui.BaseUiState
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalComposeUiApi::class)
@Stable
class MyNetworkContentState(
    val baseUiState: BaseUiState<List<Person>>,
    val followedState: MutableState<Boolean>,
    var selectedIndex: MutableState<Int>,
    val onFollowStatusChanged: (id: Int, name: String, followed: Boolean) -> Unit,
    val onGetMember: (keyword: String) -> Person?,
    val onDeleteMember: (id: Int) -> Person?,
    val onEstimated: (id: Int, favor: Boolean) -> Person?,
    resourceState: ResourceState<StateFlow<List<Person>>>,
) {
    val data: StateFlow<List<Person>>? = resourceState.data
    val isLoading: Boolean = resourceState.isLoading
    val throwError: Boolean = resourceState.throwError
}

@Composable
fun rememberMyNetworkContentState(
    baseUiState: BaseUiState<List<Person>>,
    followedState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    selectedIndex: MutableState<Int> = remember { mutableStateOf(-1) },
    resourceState: ResourceState<StateFlow<List<Person>>> = produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (profilesState.resource.status) {
            Status.SUCCESS -> { value = ResourceState(profilesState.resource.data) }
            Status.ERROR -> { value = ResourceState(throwError = true) }
            Status.LOADING -> { value = ResourceState(isLoading = true) }
        */
        value = ResourceState(baseUiState.uiState)
    }.value,
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