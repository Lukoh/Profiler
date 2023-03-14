package com.goforer.profiler.presentation.stateholder.ui.mynetwork.common

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
class PersonItemState
@OptIn(ExperimentalMaterial3Api::class)
constructor(
    var visibleDeleteBoxState: MutableState<Boolean>,
    var favorState: MutableState<Boolean>,
    var heightDpState: MutableState<Dp>,
    val dismissState: DismissState,
    val onDismissedToEstimation: (Boolean) -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPersonItemState(
    visibleDeleteBoxState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    favorState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    heightDpState: MutableState<Dp> = remember { mutableStateOf(56.dp) },
    onDismissedToEstimation: (Boolean) -> Unit,
    dismissState: DismissState = rememberDismissState(confirmValueChange = { dismissValue ->
        when (dismissValue) {
            DismissValue.Default -> {
                false
            }
            DismissValue.DismissedToEnd -> {
                onDismissedToEstimation(false)
                favorState.value = false

                false
            }
            DismissValue.DismissedToStart -> {
                onDismissedToEstimation(true)
                favorState.value = true

                false
            }
            else -> {
                false
            }
        }
    })
): PersonItemState = remember(visibleDeleteBoxState, favorState, heightDpState, dismissState, onDismissedToEstimation) {
    PersonItemState(
        visibleDeleteBoxState = visibleDeleteBoxState,
        favorState = favorState,
        heightDpState = heightDpState,
        dismissState = dismissState,
        onDismissedToEstimation = onDismissedToEstimation
    )
}