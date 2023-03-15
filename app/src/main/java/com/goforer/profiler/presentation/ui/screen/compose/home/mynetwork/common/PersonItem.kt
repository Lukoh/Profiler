/*
 * Copyright (C) 2023 The Android Open Source Project by Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.common

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
//import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.*
//import androidx.lifecycle.flowWithLifecycle
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.PersonItemState
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.rememberPersonItemState
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.common.item.DismissBackgroundContent
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.common.item.DismissContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonItem(
    modifier: Modifier = Modifier,
    sexButtonVisible: Boolean,
    person: Person,
    index: Int,
    followedState: MutableState<Boolean>,
    onItemClicked: (item: Person, index: Int) -> Unit,
    onFollowed: (Person, Boolean) -> Unit,
    onSexViewed: (String) -> Unit,
    onMemberDeleted: (Int) -> Unit,
    onEstimated: (Int, Boolean) -> Unit,
    state: PersonItemState = rememberPersonItemState(onDismissedToEstimation = {
        onEstimated(person.id, it)
    }),
    onNavigateToDetailInfo: (Int) -> Unit
) {
    /*
     * The following code implements the requirement of advancing automatically
     * to the DetailInfo screen when person id is changed....
     * and the user wanted to continue with the next process.
     */
    /*
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val currentNavigateToDetailInfo by rememberUpdatedState(onNavigateToDetailInfo)
    var clikced by remember { mutableStateOf(false) }

    LaunchedEffect(clikced, lifecycle) {
        if (clikced) {
            snapshotFlow { person.id }
                .flowWithLifecycle(lifecycle)
                .collect {
                    currentNavigateToDetailInfo(it)
                }
        }
    }

     */

    if (!person.deleted) {
        if (state.visibleDeleteBoxState.value)
            state.heightDpState.value = 92.dp
        else
            state.heightDpState.value = 56.dp

        val verticalPadding = if (index == 0)
            0.dp
        else
            2.dp

        state.favorState.value = person.favor
        SwipeToDismiss(
            state = state.dismissState,
            modifier = modifier
                .fillMaxWidth()
                .height(state.heightDpState.value)
                .padding(8.dp, verticalPadding)
                .clip(RoundedCornerShape(4.dp)),
            dismissContent = {
                DismissContent(
                    modifier = modifier,
                    sexButtonVisible = sexButtonVisible,
                    person = person,
                    index = index,
                    followedState = followedState,
                    onItemClicked = onItemClicked,
                    onFollowed = onFollowed,
                    onSexViewed = onSexViewed,
                    onMemberDeleted = onMemberDeleted,
                    onEstimated = onEstimated,
                    state = state,
                    onNavigateToDetailInfo = onNavigateToDetailInfo
                )
            },
            background = {
                DismissBackgroundContent(
                    person = person,
                    onEstimated = onEstimated,
                    state = state
                )
            }
        )
    }
}