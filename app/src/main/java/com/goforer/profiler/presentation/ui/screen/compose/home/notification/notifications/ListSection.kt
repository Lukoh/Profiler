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

package com.goforer.profiler.presentation.ui.screen.compose.home.notification.notifications

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification

@Composable
fun ListSection(
    modifier: Modifier = Modifier,
    notifications: List<Notification>,
    lazyListState: LazyListState = rememberLazyListState(),
    onItemClicked: (item: Notification, index: Int) -> Unit,
    onNavigateToDetailInfo: (Int) -> Unit
) {
    val showButton by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }
    var clicked by remember { mutableStateOf(false) }

    BoxWithConstraints(modifier = modifier) {
        LazyColumn(
            modifier = Modifier,
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(notifications, key = { _, item -> item.id }, itemContent = { index, item ->
                NotificationItem(
                    modifier,
                    item,
                    index,
                    onItemClicked = onItemClicked,
                    onNavigateToDetailInfo = onNavigateToDetailInfo
                )
                //Divider()})
            })
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
                Text("Up!")
            }
        }

        LaunchedEffect(lazyListState, showButton, clicked) {
            if (showButton && clicked)
                lazyListState.scrollToItem (0)

            clicked = false
        }

        /*
        if (showButton) {
            val coroutineScope = rememberCoroutineScope()

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(bottom = 4.dp, end = 8.dp),
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    coroutineScope.launch {
                        lazyListState.scrollToItem(0)
                    }}
            ) {
                Text("Up!")
            }
        }

         */
    }
}