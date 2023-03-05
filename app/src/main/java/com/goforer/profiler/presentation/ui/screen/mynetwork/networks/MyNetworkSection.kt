/*
 * Copyright (C) 2021 The Android Open Source Project by Lukoh Nam, goForer
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

package com.goforer.profiler.presentation.ui.screen.mynetwork.networks

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.profiler.data.source.model.entity.source.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.ui.rememberEditableInputState
import com.goforer.profiler.presentation.ui.ext.noRippleClickable

@Composable
fun MyNetworkSection(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    myNetworksState: State<List<Person>>,
    followed: MutableState<Boolean>,
    onItemClicked: (item: Person, index: Int) -> Unit,
    @SuppressLint("ModifierParameter")
    onFollowed: (Person, Boolean) -> Unit,
    onSearched: (String, Boolean) -> Unit,
    onNavigateToDetailInfo: (Int) -> Unit
) {
    val lazyListState: LazyListState = rememberLazyListState()
    val editableInputState = rememberEditableInputState(hint = "Search")
    var searchedKeyword by remember { mutableStateOf("") }
    val showButton by remember { derivedStateOf { searchedKeyword.isNotEmpty() } }
    var clicked by remember { mutableStateOf(false) }
    val myNetworks by remember(myNetworksState.value) {
        derivedStateOf { myNetworksState.value.filter { it.name.contains(searchedKeyword) } }
    }

    if (!editableInputState.isHint)
        onSearched(editableInputState.text, false)

    Box(modifier = modifier) {
        Column(
            modifier = modifier
                .padding(
                    0.dp,
                    contentPadding.calculateTopPadding(),
                    0.dp,
                    0.dp
                )
                .animateContentSize()
                .noRippleClickable { }
        ) {
            SearchSection(
                modifier = Modifier.padding(8.dp),
                state = editableInputState,
                onSearched = { keyword ->
                    myNetworksState.value.find { it.name.contains(keyword)}?.let {
                        searchedKeyword = keyword
                    }

                    onSearched(keyword, true)
                }
            )
            ListSection(
                modifier = Modifier.weight(1f),
                myNetworks,
                followed = followed,
                lazyListState = lazyListState,
                onItemClicked = onItemClicked,
                onFollowed = onFollowed,
                onNavigateToDetailInfo = onNavigateToDetailInfo
            )
        }

        AnimatedVisibility(
            visible = showButton,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(bottom = 4.dp, end = 8.dp),
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    clicked = true
                }
            ) {
                Text("Back!")
            }
        }

        if (showButton && clicked) {
            LaunchedEffect(lazyListState) {
                searchedKeyword = ""
                clicked = false
            }
        }
    }
}