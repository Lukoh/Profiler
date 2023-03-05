package com.goforer.profiler.presentation.ui.screen.mynetwork.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goforer.profiler.presentation.stateholder.business.mynetwork.MyNetworkViewModel
import com.goforer.profiler.presentation.ui.ext.noRippleClickable

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(46.dp),
    profileViewModel: MyNetworkViewModel,
    userId: Int
) {
    val profile = profileViewModel.getPerson(userId)

    profile?.let {
        Column(
            modifier = modifier
                .padding(
                    0.dp,
                    contentPadding.calculateTopPadding(),
                    0.dp,
                    0.dp
                )
                .animateContentSize()
                .noRippleClickable { }
        ) {
            PictureItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(4.dp))
            NameItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(4.dp))
            BirthdayItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(4.dp))
            CellphoneItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(4.dp))
            PersonalityItem(modifier = Modifier, it)
            Spacer(modifier = Modifier.height(4.dp))
            MemberItem(modifier = Modifier, it)
        }
    }
}