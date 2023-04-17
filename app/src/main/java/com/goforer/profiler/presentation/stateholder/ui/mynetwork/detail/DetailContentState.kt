package com.goforer.profiler.presentation.stateholder.ui.mynetwork.detail

import androidx.compose.runtime.*
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import kotlinx.coroutines.flow.StateFlow

@Stable
class DetailContentState(
    val uiStateFlow: StateFlow<List<Person>>,
    val onGetMember: (id: Int) -> Person?
)

@Composable
fun rememberDetailContentState(
    uiStateFlow: StateFlow<List<Person>>,
    onGetMember: (id: Int) -> Person?,
): DetailContentState = remember(uiStateFlow, onGetMember) {
    DetailContentState(uiStateFlow = uiStateFlow, onGetMember = onGetMember)
}