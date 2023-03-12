package com.goforer.profiler.presentation.stateholder.ui.mynetwork.common

import androidx.compose.runtime.*

@Stable
class PersonItemState(
    var visibleDeleteBoxState: MutableState<Boolean>
)

@Composable
fun rememberPersonItemState(
    visibleDeleteBoxState: MutableState<Boolean> = remember { mutableStateOf(false) }
): PersonItemState = remember(visibleDeleteBoxState) {
    PersonItemState(
        visibleDeleteBoxState = visibleDeleteBoxState
    )
}