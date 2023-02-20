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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileContent(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    profileViewModel: ProfileViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val membered = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val profilesState = profileViewModel.profiles.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    var memberName by rememberSaveable { mutableStateOf("") }

    ProfileSection(modifier = modifier, contentPadding, profilesState, membered, onMemberChanged =  { profile, changed ->
        scope.launch {
            profileViewModel.changeMemberStatus(profile.id, profile.name, changed)
            membered.value = changed
            memberName = profile.name
            if (changed) {
                keyboardController?.hide()
                snackbarHostState.showSnackbar("${profile.name} has been our member")
            } else
                snackbarHostState.showSnackbar("${profile.name} is not our member")
        }
    }, onTextChanged = { text ->
        profilesState.value.find { it.name == text }?.let {
            keyboardController?.hide()
            if (it.sex == "남성")
                Toast.makeText(context, "${it.name} is the gentlemen and our member.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "${it.name} is the lady and our  member.", Toast.LENGTH_SHORT).show()
        }
    })
}