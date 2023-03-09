package com.goforer.profiler.presentation.stateholder.ui.mynetwork.members

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.data.model.state.ResourceState
import kotlinx.coroutines.flow.StateFlow

@Stable
class MembersContentState(
    val uiState: StateFlow<List<Person>>,
    val lazyListState: LazyListState,
    val sexState: MutableState<String>,
    val followedState: MutableState<Boolean>,
    resourceState: ResourceState<StateFlow<List<Person>>>
) {
    val data: StateFlow<List<Person>>? = resourceState.data
    val isLoading: Boolean = resourceState.isLoading
    val throwError: Boolean = resourceState.throwError

    lateinit var membersState: State<List<Person>>
}

@Composable
fun rememberMembersContentState(
    uiState: StateFlow<List<Person>>,
    lazyListState: LazyListState = rememberLazyListState(),
    sexState: MutableState<String> = rememberSaveable { mutableStateOf("") },
    followedState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    resourceState: ResourceState<StateFlow<List<Person>>> = produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (profilesState.resource.status) {
            Status.SUCCESS -> { ResourceState(profilesState.resource.data) }
            Status.ERROR -> { ResourceState(throwError = true) }
            Status.LOADING -> { ResourceState(isLoading = true) }
        */
        value = ResourceState(uiState)
    }.value
): MembersContentState = remember(uiState, lazyListState, sexState, followedState, resourceState) {
    MembersContentState(
        uiState = uiState,
        lazyListState = lazyListState,
        sexState = sexState,
        followedState = followedState,
        resourceState = resourceState
    )
}