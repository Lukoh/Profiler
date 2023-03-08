
package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.networks

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goforer.profiler.data.repository.Repository.Companion.replyCount
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.data.model.state.ResourceState
import com.goforer.profiler.presentation.stateholder.business.mynetwork.MyNetworkViewModel
import com.goforer.profiler.R
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyNetworkContent(
    modifier: Modifier = Modifier,
    viewModel: MyNetworkViewModel,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    onNavigateToDetailInfo: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val followed = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val hint =  stringResource(id = R.string.placeholder_search)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val resourceState by produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (uiState.resource.status) {
            Status.SUCCESS -> { ResourceState(uiState.resource.data) }
            Status.ERROR -> { ResourceState(throwError = true) }
            Status.LOADING -> { ResourceState(isLoading = true) }
         */
        value = ResourceState(uiState)
    }

    var selectedIndex by remember { mutableStateOf(-1) }

    /*
    * Just open & trigger the below code to take data from the Backend server.
    */
    /*
     profileViewModel.trigger(2, Params("uud1234213"))
     */

    replyCount = 5
    LaunchedEffect(selectedIndex, lifecycle) {
        if (selectedIndex != -1)
            Toast.makeText(context, "Show the detailed profile!", Toast.LENGTH_SHORT).show()
    }

    when {
        resourceState.data != null -> {
            MyNetworkSection(
                modifier = modifier,
                contentPadding = contentPadding,
                state = resourceState.data as State<List<Person>>,
                followed = followed,
                onItemClicked = { _, index ->
                    selectedIndex = index
                },
                onFollowed =  { person, changed ->
                    scope.launch {
                        viewModel.changeFollowStatus(person.id, person.name, changed)
                        if (changed) {
                            keyboardController?.hide()
                            snackbarHostState.showSnackbar("${person.name} has been our member")
                        } else
                            snackbarHostState.showSnackbar("${person.name} is not our member")
                    }
                },
                onSearched = { name, byClicked ->
                    uiState.value.find { it.name == name }?.let {
                        keyboardController?.hide()
                    }

                    uiState.value.find { it.name == name } ?: if (byClicked) {
                        keyboardController?.hide()
                        if (name != hint)
                            Toast.makeText(
                                context,
                                "$name is not our member.",
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