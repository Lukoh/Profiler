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

package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.networks

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.ui.rememberEditableInputState
import com.goforer.profiler.presentation.ui.ext.noRippleClickable
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme

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

    BoxWithConstraints(modifier = modifier) {
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

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
    showSystemUi = true
)
@Composable
fun MyNetworkSectionPreview(modifier: Modifier = Modifier) {
    ProfilerTheme {
        BoxWithConstraints(modifier = modifier) {
            Column(
                modifier = modifier
                    .padding(
                        0.dp,
                        56.dp,
                        0.dp,
                        0.dp
                    )
                    .animateContentSize()
                    .noRippleClickable { }
            ) {
                val editableInputState = rememberEditableInputState(hint = "Search")
                val followed = rememberSaveable { mutableStateOf(true) }
                val lazyListState: LazyListState = rememberLazyListState()
                val myNetworks = mutableListOf(
                    Person(0,"LLyyiok", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "sociable & gregarious", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(1,"Afredo", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "gregarious & friendly", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(2,"Aliche", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(3,"Tina", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4","affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(4,"Lolly", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4","affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(5,"Hassen", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4","affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(6,"Lyll", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "sociable & gregarious", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(7,"Kevin", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(8,"Tony", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(9,"Steven", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective."),
                    Person(10,"Micle", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4", "affable & convivial", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective.")
                )

                SearchSection(
                    modifier = Modifier.padding(8.dp),
                    state = editableInputState,
                    onSearched = {}
                )
                ListSection(
                    modifier = Modifier.weight(1f),
                    myNetworks,
                    followed = followed,
                    lazyListState = lazyListState,
                    onItemClicked = { _, _ -> },
                    onFollowed = { _, _ -> },
                    onNavigateToDetailInfo = {}
                )
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
                    Text("Back!")
                }
            }
        }
    }
}