package com.goforer.profiler.presentation.stateholder.ui.mynetwork.detail

import androidx.compose.runtime.*
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import kotlinx.coroutines.flow.StateFlow

@Stable
class DetailContentState(
    val uiState: StateFlow<List<Person>>,
    val onGetPerson: (id: Int) -> Person?
)

@Composable
fun rememberDetailContentState(
    uiState: StateFlow<List<Person>>,
    onGetPerson: (id: Int) -> Person?,
): DetailContentState = remember(uiState, onGetPerson) {
    DetailContentState(uiState = uiState, onGetPerson = onGetPerson)
}