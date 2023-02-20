package com.goforer.composetest.presentation.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
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
import com.goforer.composetest.data.source.model.entity.profile.Profile
import com.goforer.composetest.presentation.ui.profile.ProfileScreen
import com.goforer.composetest.presentation.ui.profile.ProfileSection
import com.goforer.composetest.presentation.ui.theme.ComposeTestTheme
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
        )
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen(modifier = Modifier)
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
            Profile(0,"Lukoh", "남성", true),
            Profile(1,"Alex", "남성", false),
            Profile(2,"Alice", "여성", false),
            Profile(3,"Tana", "여성", false),
            Profile(4,"Lona", "여성", true),
            Profile(5,"Kevin", "남성", true),
            Profile(6,"Jully", "여성", true),
            Profile(7,"Kevin", "남성", true),
            Profile(8,"Tom", "남성", false),
            Profile(9,"Steven", "남성", false),
            Profile(10,"Micle", "남성", false)
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

                ProfileSection(modifier = modifier, innerPadding, profilesState, isChecked, onMemberChanged = { profile, changed ->
                    scope.launch {
                        profile.membered = changed
                        isChecked.value = changed
                        memberName = profile.name
                    }
                }, onTextChanged = {

                })
            }
        )
    }
}