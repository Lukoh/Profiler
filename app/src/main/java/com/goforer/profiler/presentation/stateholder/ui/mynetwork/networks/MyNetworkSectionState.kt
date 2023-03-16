package com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks.MyNetworkSectionState.Companion.noneAction

@Stable
class MyNetworkSectionState(
    val lazyListState: LazyListState,
    val editableInputState: EditableInputState,
    var searchedKeywordState: MutableState<String>,
    val showButtonState: State<Boolean>,
    var clickedState: MutableState<Boolean>,
    var refreshActionState: MutableState<Int>
) {
    companion object {
        internal const val noneAction = 0
        internal const val searchAction = 1
        internal const val deleteAction = 2
    }

    lateinit var currentNetworksState: State<List<Person>>
}

@Composable
fun rememberMyNetworkSectionState(
    lazyListState: LazyListState = rememberLazyListState(),
    editableInputState: EditableInputState = rememberEditableInputState(hint = "Search"),
    searchedKeywordState: MutableState<String> = rememberSaveable { mutableStateOf("") },
    showButtonState: State<Boolean> = remember { derivedStateOf { searchedKeywordState.value.isNotEmpty() } },
    clickedState: MutableState<Boolean> = remember { mutableStateOf(false) },
    refreshActionState: MutableState<Int> = rememberSaveable { mutableStateOf(noneAction)}
): MyNetworkSectionState = remember(
    lazyListState, editableInputState, searchedKeywordState, showButtonState, clickedState, refreshActionState) {
    MyNetworkSectionState(
        lazyListState = lazyListState,
        editableInputState =  editableInputState,
        searchedKeywordState = searchedKeywordState,
        showButtonState = showButtonState,
        clickedState = clickedState,
        refreshActionState = refreshActionState
    )
}