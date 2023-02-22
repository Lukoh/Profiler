package com.goforer.composetest.presentation.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.goforer.composetest.presentation.ui.landing.LandingScreen
import com.goforer.composetest.presentation.ui.profile.ProfileScreen

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