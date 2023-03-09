package com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import com.goforer.profiler.data.model.datum.response.mynetwork.Person

@Stable
class MyNetworkSectionState(
    val lazyListState: LazyListState,
    val editableInputState: EditableInputState,
    var searchedKeywordState: MutableState<String>,
    val showButtonState: State<Boolean>,
    var clickedState: MutableState<Boolean>
) {
    lateinit var currentNetworksState: State<List<Person>>
}

@Composable
fun rememberMyNetworkSectionState(
    lazyListState: LazyListState = rememberLazyListState(),
    editableInputState: EditableInputState = rememberEditableInputState(hint = "Search"),
    searchedKeywordState: MutableState<String> = remember { mutableStateOf("") },
    showButtonState: State<Boolean> = remember { derivedStateOf { searchedKeywordState.value.isNotEmpty() } },
    clickedState: MutableState<Boolean> = remember { mutableStateOf(false) },
): MyNetworkSectionState = remember(
    lazyListState, editableInputState, searchedKeywordState, showButtonState, clickedState) {
    MyNetworkSectionState(
        lazyListState = lazyListState,
        editableInputState =  editableInputState,
        searchedKeywordState = searchedKeywordState,
        showButtonState = showButtonState,
        clickedState = clickedState)
}