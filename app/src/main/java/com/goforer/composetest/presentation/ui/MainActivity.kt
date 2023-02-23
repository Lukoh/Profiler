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

package com.goforer.composetest.presentation.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.SnackbarDuration.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.goforer.composetest.data.source.model.entity.source.profile.Profile
import com.goforer.composetest.presentation.ui.profile.ProfileSection
import com.goforer.composetest.presentation.ui.theme.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        internal const val SplashWaitTime = 1200L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
    showSystemUi = true
)
@Composable
private fun PreviewShowContainer() {
    ComposeTestTheme {
        val profileList = mutableListOf(
            Profile(0,"Lukoh", "남성", true, "https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(1,"Alex", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(2,"Alice", "여성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(3,"Tana", "여성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(4,"Lona", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(5,"Kevin", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(6,"Jully", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(7,"Kevin", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(8,"Tom", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(9,"Steven", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
            Profile(10,"Micle", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4")
        )
        val profilesState: State<List<Profile>> = rememberSaveable { mutableStateOf<List<Profile>>(profileList) }
        val isChecked = rememberSaveable { mutableStateOf(false) }
        val modifier = Modifier

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Centered TopAppBar",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->
                val scope = rememberCoroutineScope()

                var memberName by rememberSaveable { mutableStateOf("") }

                ProfileSection(
                    modifier = modifier,
                    contentPadding = innerPadding,
                    profilesState = profilesState,
                    membered = isChecked,
                    onItemClicked = { _, _ ->  },
                    onMemberChanged = { profile, changed ->
                        scope.launch {
                            profile.membered = changed
                            isChecked.value = changed
                            memberName = profile.name
                        }
                    }, onSearched = { _, _ ->

                    })
            }
        )
    }
}