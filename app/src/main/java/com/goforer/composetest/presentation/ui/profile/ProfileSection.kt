package com.goforer.composetest.presentation.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.composetest.data.source.model.entity.profile.Profile

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    profilesState: State<List<Profile>>,
    isChecked: MutableState<Boolean>,
    lazyListState: LazyListState = rememberLazyListState(),
    @SuppressLint("ModifierParameter")
    onChecked: (Profile, Boolean) -> Unit,
    onTextChanged: (String) -> Unit,
) {
    Column(modifier = modifier.padding(0.dp, contentPadding.calculateTopPadding(), 0.dp, 0.dp)) {
        SearchSection(modifier = Modifier.padding(8.dp), onTextChanged)
        LazyColumn(
            modifier = Modifier,
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(profilesState.value, key = { profile -> profile.id }) {item ->
                ProfileItem(modifier, item, isChecked, onChecked = onChecked)
                //Divider()
            }
        }
    }
}