/*
 * Copyright (C) 2021 The Android Open Source Project by Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.detail

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goforer.profiler.data.source.model.entity.source.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.business.mynetwork.MyNetworkViewModel
import com.goforer.profiler.presentation.ui.ext.noRippleClickable
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(46.dp),
    profileViewModel: MyNetworkViewModel,
    userId: Int
) {
    val person = profileViewModel.getPerson(userId)

    person?.let {
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
            SexItem(modifier = Modifier, person)
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

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
    showSystemUi = true
)
@Composable
fun MyNetworkSectionPreview(modifier: Modifier = Modifier) {
    ProfilerTheme {
        Column(
            modifier = modifier
                .padding(
                    0.dp,
                    56.dp,
                    0.dp,
                    0.dp
                )
                .animateContentSize()
                .noRippleClickable { }
        ) {
            val person = Person(0,"LLyyiok", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "sociable & gregarious", "+820101111-1111","", "Mar, 04, 1999")

            PictureItem(modifier = Modifier, person)
            Spacer(modifier = Modifier.height(4.dp))
            NameItem(modifier = Modifier, person)
            Spacer(modifier = Modifier.height(4.dp))
            SexItem(modifier = Modifier, person)
            Spacer(modifier = Modifier.height(4.dp))
            BirthdayItem(modifier = Modifier, person)
            Spacer(modifier = Modifier.height(4.dp))
            CellphoneItem(modifier = Modifier, person)
            Spacer(modifier = Modifier.height(4.dp))
            PersonalityItem(modifier = Modifier, person)
            Spacer(modifier = Modifier.height(4.dp))
            MemberItem(modifier = Modifier, person)
        }
    }
}