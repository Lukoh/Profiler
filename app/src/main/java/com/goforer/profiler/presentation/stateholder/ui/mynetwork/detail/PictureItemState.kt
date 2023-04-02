package com.goforer.profiler.presentation.stateholder.ui.mynetwork.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Stable
class PictureItemState(
    val openBottomSheet: MutableState<Boolean>,
    val scope: CoroutineScope,
    val bottomSheetState: SheetState
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPictureItemState(
    openBottomSheet: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    skipPartiallyExpanded: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    scope: CoroutineScope = rememberCoroutineScope(),
    bottomSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
): PictureItemState = remember(openBottomSheet, skipPartiallyExpanded, scope, bottomSheetState) {
    PictureItemState(
        openBottomSheet = openBottomSheet,
        scope = scope,
        bottomSheetState = bottomSheetState
    )
}