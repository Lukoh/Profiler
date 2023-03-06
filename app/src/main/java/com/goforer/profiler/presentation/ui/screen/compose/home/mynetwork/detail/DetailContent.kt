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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    contentPadding: PaddingValues = PaddingValues(4.dp),
    profileViewModel: MyNetworkViewModel,
    userId: Int
) {
    val person = profileViewModel.getPerson(userId)
    val scrollState = rememberScrollState()

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
                .verticalScroll(scrollState)
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
            Spacer(modifier = Modifier.height(16.dp))
            ReputationItem(modifier = Modifier, it)
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
        val scrollState = rememberScrollState()

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
                .verticalScroll(scrollState)
        ) {
            val person = Person(0,"LLyyiok", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "sociable & gregarious", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective. He is proficient in managing projects with consistent and successful results.\n" +
                    "I am confident that his leadership experience and expertise in SW development will make him a good SW engineer who works with many colleagues, and should come up with creative awesome ideas.\n" +
                    "He is an expert and architect in Android application development which has resulted in excellent reviews from all collegue. Lukoh is an honest and hardworking team lead, always willing to pitch in to help the team. He is efficient in planning projects, punctual in meeting deadlines, and conscientiously adheres to company standards and guidelines. On the other he understands the technical design and development, techniques and constraints. Lukoh has a true talent for communicating and negotiating where the outcome is beneficial for all involved. He is absolutely a valuable strength to any team as team lead!")

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
            Spacer(modifier = Modifier.height(16.dp))
            ReputationItem(modifier = Modifier, person)
        }
    }
}