package com.goforer.composetest.presentation.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.composetest.data.source.model.entity.source.profile.Profile
import kotlinx.coroutines.launch

@Composable
fun ListSection(
    modifier: Modifier = Modifier,
    profilesState: State<List<Profile>>,
    membered: MutableState<Boolean>,
    lazyListState: LazyListState = rememberLazyListState(),
    onItemClicked: (item: Profile, index: Int) -> Unit,
    @SuppressLint("ModifierParameter")
    onMemberChanged: (Profile, Boolean) -> Unit,
) {
    Box(modifier = modifier) {
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