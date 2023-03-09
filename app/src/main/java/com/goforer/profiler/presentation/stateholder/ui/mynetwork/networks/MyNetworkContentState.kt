package com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.Lifecycle
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.data.model.state.ResourceState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalComposeUiApi::class)
@Stable
class MyNetworkContentState(
    val uiState: StateFlow<List<Person>>,
    val context: Context,
    val scope: CoroutineScope,
    val lifecycle: Lifecycle,
    val followedState: MutableState<Boolean>,
    var selectedIndex: MutableState<Int>,
    val keyboardController: SoftwareKeyboardController?,
    val onFollowStatusChanged: (id: Int, name: String, followed: Boolean) -> Unit,
    resourceState: ResourceState<StateFlow<List<Person>>>,
) {
    val data: StateFlow<List<Person>>? = resourceState.data
    val isLoading: Boolean = resourceState.isLoading
    val throwError: Boolean = resourceState.throwError
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberMyNetworkContentState(
    uiState: StateFlow<List<Person>>,
    context: Context = LocalContext.current,
    scope: CoroutineScope = rememberCoroutineScope(),
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    followedState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    selectedIndex: MutableState<Int> = remember { mutableStateOf(-1) },
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    resourceState: ResourceState<StateFlow<List<Person>>> = produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (profilesState.resource.status) {
            Status.SUCCESS -> { ResourceState(profilesState.resource.data) }
            Status.ERROR -> { ResourceState(throwError = true) }
            Status.LOADING -> { ResourceState(isLoading = true) }
        */
        value = ResourceState(uiState)
    }.value,
    onFollowStatusChanged: (id: Int, name: String, followed: Boolean) -> Unit
): MyNetworkContentState = remember(
    uiState, context, scope, lifecycle, followedState, selectedIndex,
    keyboardController, resourceState, onFollowStatusChanged
) {
    MyNetworkContentState(
        uiState = uiState,
        context = context,
        scope = scope,
        lifecycle = lifecycle,
        followedState = followedState,
        selectedIndex = selectedIndex,
        keyboardController = keyboardController,
        resourceState = resourceState,
        onFollowStatusChanged = onFollowStatusChanged
    )
}