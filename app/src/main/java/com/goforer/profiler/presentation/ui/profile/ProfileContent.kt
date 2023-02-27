@file:Suppress("UNCHECKED_CAST")

package com.goforer.profiler.presentation.ui.profile

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goforer.profiler.data.repository.Repository.Companion.replyCount
import com.goforer.profiler.data.source.model.entity.source.profile.Profile
import com.goforer.profiler.data.source.model.entity.state.ResourceState
import com.goforer.profiler.presentation.stateholder.business.profile.ProfileViewModel
import com.goforer.profiler.R
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    onNavigateToDetailInfo: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val membered = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val profileViewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>()
    val profilesState = profileViewModel.profiles.collectAsStateWithLifecycle()
    val hint =  stringResource(id = R.string.placeholder_search)
    val resourceState by produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (profilesState.resource.status) {
            Status.SUCCESS -> { ResourceState(profilesState.resource.data) }
            Status.ERROR -> { ResourceState(throwError = true) }
            Status.LOADING -> { ResourceState(isLoading = true) }
         */
        value = ResourceState(profilesState)
    }

    var selectedIndex by remember { mutableStateOf(-1) }

    /*
    * Just open & trigger the below code to take data from the Backend server.
    */
    /*
     profileViewModel.trigger(2, Params("uud1234213"))
     */

    replyCount = 5
    LaunchedEffect(selectedIndex) {
        if (selectedIndex != -1)
            Toast.makeText(context, "Show the detailed profile!", Toast.LENGTH_SHORT).show()
    }

    when {
        resourceState.data != null -> {
            ProfileSection(
                modifier = modifier, contentPadding,
                profilesState = resourceState.data as State<List<Profile>>,
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
                        if (text != hint)
                            Toast.makeText(
                                context,
                                "$text is not our member.",
                                Toast.LENGTH_SHORT
                            ).show()
                    } else {
                        Timber.d("is texted by typing")
                    }
                },
                onNavigateToDetailInfo = onNavigateToDetailInfo
            )
        }
        resourceState.isLoading -> { }
        resourceState.throwError -> { }
    }
}