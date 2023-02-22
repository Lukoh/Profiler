package com.goforer.composetest.presentation.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.composetest.data.source.model.entity.profile.Profile
import com.goforer.composetest.presentation.ui.ext.noRippleClickable

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    profilesState: State<List<Profile>>,
    membered: MutableState<Boolean>,
    lazyListState: LazyListState = rememberLazyListState(),
    onItemClicked: (item:Profile, index: Int) -> Unit,
    @SuppressLint("ModifierParameter")
    onMemberChanged: (Profile, Boolean) -> Unit,
    onTextChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier.padding(0.dp, contentPadding.calculateTopPadding(), 0.dp, 0.dp)
            .noRippleClickable {  }
    ) {
        SearchSection(modifier = Modifier.padding(8.dp), onTextChanged)
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
    }
}