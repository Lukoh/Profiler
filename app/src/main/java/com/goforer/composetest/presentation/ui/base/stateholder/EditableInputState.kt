package com.goforer.composetest.presentation.ui.base.stateholder

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

class EditableInputState(private val hint: String, initialText: String) {
    var text by mutableStateOf(initialText)

    val isHint: Boolean
        get() = text == hint

    companion object {
        val Saver: Saver<EditableInputState, *> = listSaver(
            save = { listOf(it.hint, it.text) },
            restore = {
                EditableInputState(
                    hint = it[0],
                    initialText = it[1],
                )
            }
        )
    }
}

@Composable
fun rememberEditableInputState(hint: String): EditableInputState =
    rememberSaveable(hint, saver = EditableInputState.Saver) {
        EditableInputState(hint, hint)
    }