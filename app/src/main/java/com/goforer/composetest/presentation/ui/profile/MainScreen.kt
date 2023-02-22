package com.goforer.composetest.presentation.ui.profile

import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun MainScreen() {
    Surface(color = MaterialTheme.colorScheme.primary) {
        var showLandingScreen by remember { mutableStateOf(true) }

        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            ProfileScreen()
        }
    }
}