package com.goforer.profiler.presentation.stateholder.ui.notification.notifications

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Stable
class ListSectionState(
    val lazyListState: LazyListState,
    val showButton: State<Boolean>,
    var clicked: MutableState<Boolean>
)

@Composable
fun rememberListSectionState(
    lazyListState: LazyListState = rememberLazyListState(),
    showButton: State<Boolean> = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } },
    clicked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
): ListSectionState = remember(lazyListState, showButton, clicked) {
    ListSectionState(
        lazyListState = lazyListState,
        showButton = showButton,
        clicked = clicked
    )
}