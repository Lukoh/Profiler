package com.goforer.profiler.presentation.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.profiler.R
import com.goforer.profiler.data.source.model.entity.source.profile.Profile

@Composable
fun MemberItem(
    modifier: Modifier = Modifier,
    profile: Profile
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(8.dp, 0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(colorResource(id = R.color.colorSystemBgSecondary))
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        ) {
            Text(
                stringResource(id = R.string.profile_detail_member),
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                if (profile.membered)
                    stringResource(id = R.string.profile_detail_is_member)
                else
                    stringResource(id = R.string.profile_detail_is_not_member),
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                color = colorResource(id = R.color.teal_700),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}