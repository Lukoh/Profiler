package com.goforer.profiler.presentation.ui.profile.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.profiler.presentation.stateholder.business.profile.ProfileViewModel
import com.goforer.profiler.presentation.ui.ext.noRippleClickable

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    profileViewModel: ProfileViewModel,
    userId: Int
) {
    val profile = profileViewModel.getProfile(userId)

    profile?.let {
        Column(
            modifier = modifier
                .padding(
                    0.dp,
                    contentPadding.calculateTopPadding(),
                    0.dp,
                    contentPadding.calculateBottomPadding()
                )
                .noRippleClickable { }
        ) {
            NameItem(modifier = Modifier, it)
        }
    }
}