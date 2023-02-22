package com.goforer.composetest.presentation.ui.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.composetest.R
import com.goforer.composetest.data.source.model.entity.profile.Profile

@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    profile: Profile,
    index: Int,
    membered: MutableState<Boolean>,
    onItemClicked: (item: Profile, index: Int) -> Unit,
    onMemberChanged: (Profile, Boolean) -> Unit
) {
    val context = LocalContext.current

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
                .clickable {
                    onItemClicked(profile, index)
                },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_headlait_notification),
                contentDescription = "ComposeTest",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(4.dp)
                    .requiredSize(40.dp)
                    //.wrapContentSize()
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.secondary, CircleShape),
                Alignment.CenterStart,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()) {
                Text(
                    profile.name,
                    modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Normal,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    profile.sex,
                    modifier = Modifier
                        .paddingFromBaseline(4.dp)
                        .offset(x = 0.dp, y = (-2).dp),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Normal,
                    color = colorResource(id = R.color.teal_700),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1f))
            Row(modifier = Modifier.wrapContentWidth()) {
                membered.value = profile.membered
                Surface(modifier = Modifier.align(Alignment.CenterVertically), shape = MaterialTheme.shapes.small, shadowElevation = 1.dp) {
                    Text(
                        stringResource(id = R.string.member_check),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(6.dp, 2.dp, 6.dp, 2.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = colorResource(id = R.color.purple_700),
                        fontStyle = FontStyle.Normal
                    )
                }
                Spacer(modifier = Modifier
                    .height(4.dp)
                    .width(4.dp))
                Checkbox(
                    checked = membered.value,
                    onCheckedChange = {
                        membered.value = it
                        onMemberChanged(profile, it)
                    }
                )
            }
        }
    }
}