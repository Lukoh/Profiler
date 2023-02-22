package com.goforer.composetest.presentation.ui.profile

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goforer.composetest.presentation.stateholder.ProfileViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    profileViewModel: ProfileViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val membered = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val profilesState = profileViewModel.profiles.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    var selectedIndex by remember { mutableStateOf(-1) }

    LaunchedEffect(selectedIndex) {
        if (selectedIndex != -1)
            Toast.makeText(context, "Show the detailed profile!", Toast.LENGTH_SHORT).show()
    }

    ProfileSection(
        modifier = modifier, contentPadding,
        profilesState = profilesState,
        membered = membered,
        onItemClicked = { item, index ->
            selectedIndex = index
            Toast.makeText(context, "${item.name} & Selected Index : $selectedIndex", Toast.LENGTH_SHORT).show()
        },
        onMemberChanged =  { profile, changed ->
            scope.launch {
                profileViewModel.changeMemberStatus(profile.id, profile.name, changed)
                membered.value = changed
                if (changed) {
                    keyboardController?.hide()
                    snackbarHostState.showSnackbar("${profile.name} has been our member")
                } else
                    snackbarHostState.showSnackbar("${profile.name} is not our member")
            }
        },
        onSearched = { text, byClicked ->
            profilesState.value.find { it.name == text }?.let {
                keyboardController?.hide()
                if (it.sex == "남성")
                    Toast.makeText(
                        context,
                        "${it.name} is the gentlemen and our member.",
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    Toast.makeText(
                        context,
                        "${it.name} is the lady and our  member.",
                        Toast.LENGTH_SHORT
                    ).show()
            }

            profilesState.value.find { it.name == text } ?: if (byClicked) {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    "$text is not our member.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Timber.d("is texted by typing")
            }
        }
    )
}