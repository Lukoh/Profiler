package com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import com.goforer.profiler.data.model.datum.response.mynetwork.Person

@Stable
class MyNetworkSectionState(
    val lazyListState: LazyListState,
    val editableInputState: EditableInputState,
    var searchedKeyword: String,
    val showButton: Boolean,
    var clicked: Boolean
) {
    lateinit var myNetworksState: State<List<Person>>
}

@Composable
fun rememberMyNetworkSectionState(
    lazyListState: LazyListState = rememberLazyListState(),
    editableInputState: EditableInputState = rememberEditableInputState(hint = "Search"),
    searchedKeyword: String = remember { mutableStateOf("") }.value,
    showButton: Boolean = remember { derivedStateOf { searchedKeyword.isNotEmpty() } }.value,
    clicked: Boolean = remember { mutableStateOf(false) }.value,
): MyNetworkSectionState = remember(lazyListState, editableInputState, searchedKeyword, showButton, clicked) {
    MyNetworkSectionState(lazyListState, editableInputState, searchedKeyword, showButton, clicked)
}