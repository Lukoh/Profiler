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

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.ListSectionState
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.rememberListSectionState
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme

@Composable
fun ListSection(
    modifier: Modifier = Modifier,
    state: ListSectionState = rememberListSectionState(),
    onItemClicked: (item: Person, index: Int) -> Unit,
    @SuppressLint("ModifierParameter")
    onFollowed: (Person, Boolean) -> Unit,
    onSexViewed: (String) -> Unit,
    onMemberDeleted: (Int) -> Unit,
    onEstimated: (Int, Boolean) -> Unit,
    onNavigateToDetailInfo: (Int) -> Unit
) {
    BoxWithConstraints(modifier = modifier) {
        LazyColumn(
            modifier = Modifier,
            state = state.lazyListState,
        ) {
            itemsIndexed(state.membersState.value, key = { _, item -> item.id }, itemContent = { index, item ->
                PersonItem(
                    modifier,
                    sexButtonVisible = state.visibleSexButtonState.value,
                    person = item,
                    index = index,
                    followedState = state.followedState,
                    onItemClicked = onItemClicked,
                    onFollowed = onFollowed,
                    onSexViewed = onSexViewed,
                    onMemberDeleted = onMemberDeleted,
                    onEstimated = onEstimated,
                    onNavigateToDetailInfo = onNavigateToDetailInfo
                )
                //Divider()})
            })
        }

        AnimatedVisibility(
            visible = state.visibleUpButtonState.value,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(bottom = 4.dp, end = 8.dp),
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    state.clickedState.value = true
                }
            ) {
                Text("Up!")
            }
        }

        LaunchedEffect(state.lazyListState, state.visibleUpButtonState.value, state.clickedState.value) {
            if (state.visibleUpButtonState.value && state.clickedState.value)
                state.lazyListState.scrollToItem (0)

            state.clickedState.value = false
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

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
    showSystemUi = true
)
@Composable
fun ListSectionPreview(modifier: Modifier = Modifier) {
    ProfilerTheme {
        val followedState = rememberSaveable { mutableStateOf(true) }
        val lazyListState: LazyListState = rememberLazyListState()
        val myNetworks = mutableListOf(
            Person(0,"LLyyiok", "남성", true,true,"https://avatars.githubusercontent.com/u/18302717?v=4", "sociable & gregarious", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(1,"Afredo", "남성", true,true,"https://avatars.githubusercontent.com/u/18302717?v=4", "gregarious & friendly", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(2,"Aliche", "여성", true,true,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(3,"Tina", "여성", false,true,"https://avatars.githubusercontent.com/u/18302717?v=4","affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(4,"Lolly", "여성", false,true,"https://avatars.githubusercontent.com/u/18302717?v=4","affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(5,"Hassen", "남성", false,true,"https://avatars.githubusercontent.com/u/18302717?v=4","affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(6,"Lyll", "여성", true,true,"https://avatars.githubusercontent.com/u/18302717?v=4", "sociable & gregarious", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(7,"Kevin", "남성", false,true,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(8,"Tony", "남성", false,true,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(9,"Steven", "남성", true,false,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false),
            Person(10,"Micle", "남성", true,false,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.", false)
        )

        BoxWithConstraints(modifier = modifier) {
            LazyColumn(
                modifier = Modifier,
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(
                    myNetworks,
                    key = { _, item -> item.id },
                    itemContent = { index, item ->
                        PersonItem(
                            modifier,
                            sexButtonVisible = true,
                            person = item,
                            index = index,
                            followedState = followedState,
                            onItemClicked = { _, _ -> },
                            onFollowed = { _, _ -> },
                            onSexViewed = {},
                            onMemberDeleted = {},
                            onEstimated = { _, _ -> },
                            onNavigateToDetailInfo = {}
                        )
                        //Divider()})
                    })
            }

            AnimatedVisibility(
                visible = true,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .navigationBarsPadding()
                        .padding(bottom = 4.dp, end = 8.dp),
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                    }
                ) {
                    Text("Up!")
                }
            }
        }
    }
}