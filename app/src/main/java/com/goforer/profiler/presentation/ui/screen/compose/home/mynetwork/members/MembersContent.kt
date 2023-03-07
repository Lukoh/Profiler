package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.members

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goforer.profiler.data.source.model.entity.source.response.mynetwork.Person
import com.goforer.profiler.data.source.model.entity.state.ResourceState
import com.goforer.profiler.presentation.stateholder.business.mynetwork.MembersViewModel
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.networks.ListSection

@Composable
fun MembersContent(
    modifier: Modifier = Modifier,
    membersViewModel: MembersViewModel,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    onItemClicked: (item: Person, index: Int) -> Unit,
    onFollowed: (Person, Boolean) -> Unit
) {
    val membersState = membersViewModel.members.collectAsStateWithLifecycle()
    val lazyListState: LazyListState = rememberLazyListState()
    val followed = rememberSaveable { mutableStateOf(false) }
    val resourceState by produceState(initialValue = ResourceState()) {
        // will be changed if the data come from Backend Server like below:
        /*
        when (profilesState.resource.status) {
            Status.SUCCESS -> { ResourceState(profilesState.resource.data) }
            Status.ERROR -> { ResourceState(throwError = true) }
            Status.LOADING -> { ResourceState(isLoading = true) }
         */
        value = ResourceState(membersState)
    }

    when {
        resourceState.data != null -> {
            Box(modifier = modifier.padding(
                0.dp,
                contentPadding.calculateTopPadding(),
                0.dp,
                0.dp
            )) {
                @Suppress("UNCHECKED_CAST")
                ListSection(
                    modifier = Modifier,
                    membersState.value,
                    followed = followed,
                    lazyListState = lazyListState,
                    onItemClicked = onItemClicked,
                    onFollowed = onFollowed,
                    onNavigateToDetailInfo = {
                    }
                )
            }
        }
        resourceState.isLoading -> { }
        resourceState.throwError -> { }
    }
}