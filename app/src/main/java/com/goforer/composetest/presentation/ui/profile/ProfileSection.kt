package com.goforer.composetest.presentation.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.composetest.data.source.model.entity.source.profile.Profile
import com.goforer.composetest.presentation.ui.base.stateholder.rememberEditableInputState
import com.goforer.composetest.presentation.ui.ext.noRippleClickable

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    profilesState: State<List<Profile>>,
    membered: MutableState<Boolean>,
    lazyListState: LazyListState = rememberLazyListState(),
    onItemClicked: (item: Profile, index: Int) -> Unit,
    @SuppressLint("ModifierParameter")
    onMemberChanged: (Profile, Boolean) -> Unit,
    onSearched: (String, Boolean) -> Unit,
) {
    val editableInputState = rememberEditableInputState(hint = "Search")

    if (!editableInputState.isHint)
        onSearched(editableInputState.text, false)

    Column(
        modifier = modifier.padding(0.dp, contentPadding.calculateTopPadding(), 0.dp, contentPadding.calculateBottomPadding())
            .noRippleClickable {  }
    ) {
        SearchSection(
            modifier = Modifier.padding(8.dp),
            state = editableInputState,
            onSearched = {
                onSearched(it, true)
            }
        )
        ListSection(
            modifier = Modifier.weight(1f),
            profilesState = profilesState,
            membered = membered,
            lazyListState = lazyListState,
            onItemClicked = onItemClicked,
            onMemberChanged = onMemberChanged
        )
    }
}