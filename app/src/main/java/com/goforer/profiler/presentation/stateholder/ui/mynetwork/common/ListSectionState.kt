package com.goforer.profiler.presentation.stateholder.ui.mynetwork.common

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import com.goforer.profiler.data.model.datum.response.mynetwork.Person

@Stable
class ListSectionState(
    val lazyListState: LazyListState,
    val visibleUpButtonState: State<Boolean>,
    val visibleSexButtonState: State<Boolean>,
    var clickedState: MutableState<Boolean>,
    val personsState: State<List<Person>>,
    val followedState: MutableState<Boolean>,
)

@Composable
fun rememberListSectionState(
    lazyListState: LazyListState = rememberLazyListState(),
    visibleUpButtonState: State<Boolean> = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } },
    visibleSexButtonState: MutableState<Boolean> = remember { mutableStateOf(false) },
    clickedState: MutableState<Boolean> = remember { mutableStateOf(false) },
    personsState: State<List<Person>> = remember { mutableStateOf(listOf()) },
    followedState: MutableState<Boolean> = remember { mutableStateOf(false) }
): ListSectionState = remember(lazyListState, visibleUpButtonState, visibleSexButtonState, clickedState) {
    ListSectionState(
        lazyListState = lazyListState,
        visibleUpButtonState = visibleUpButtonState,
        visibleSexButtonState = visibleSexButtonState,
        clickedState = clickedState,
        personsState = personsState,
        followedState = followedState
    )
}