package com.goforer.profiler.presentation.stateholder.ui

sealed class UiState {
    object CurrentState : UiState()
    object InProgress : UiState()
    object Error : UiState()
}
