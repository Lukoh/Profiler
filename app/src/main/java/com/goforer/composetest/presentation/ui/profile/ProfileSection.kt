package com.goforer.composetest.presentation.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.composetest.data.source.model.entity.source.profile.Profile
import com.goforer.composetest.presentation.ui.base.stateholder.rememberEditableInputState
import com.goforer.composetest.presentation.ui.ext.noRippleClickable
import kotlinx.coroutines.launch

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
        Box(Modifier.weight(1f)) {
            LazyColumn(
                modifier = Modifier,
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(profilesState.value, key = { _, item -> item.id }, itemContent = { index, item ->
                    ProfileItem(
                        modifier,
                        item,
                        index,
                        membered,
                        onItemClicked = onItemClicked,
                        onMemberChanged = onMemberChanged
                    )
                    //Divider()})
                })
            }

            val showButton by remember {
                derivedStateOf {
                    lazyListState.firstVisibleItemIndex > 0
                }
            }
            if (showButton) {
                val coroutineScope = rememberCoroutineScope()

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .navigationBarsPadding()
                        .padding(bottom = 4.dp, end = 8.dp),
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        coroutineScope.launch {
                            lazyListState.scrollToItem(0)
                        }}
                ) {
                    Text("Up!")
                }
            }
        }
    }
}