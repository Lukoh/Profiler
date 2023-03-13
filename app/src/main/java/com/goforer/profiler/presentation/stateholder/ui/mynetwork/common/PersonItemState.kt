package com.goforer.profiler.presentation.stateholder.ui.mynetwork.common

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Stable
class PersonItemState(
    var visibleDeleteBoxState: MutableState<Boolean>
)

@Composable
fun rememberPersonItemState(
    visibleDeleteBoxState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
): PersonItemState = remember(visibleDeleteBoxState) {
    PersonItemState(
        visibleDeleteBoxState = visibleDeleteBoxState
    )
}