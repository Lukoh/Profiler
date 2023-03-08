/*
 * Copyright (C) 2023 The Android Open Source Project by Lukoh Nam, goForer
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

package com.goforer.profiler.presentation.ui.screen.compose.home.notification.content

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.profiler.data.model.datum.response.notification.Notification
import com.goforer.profiler.presentation.ui.theme.ColorBgSecondary
import com.goforer.profiler.presentation.ui.theme.ColorText4
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme

@Composable
fun ContentItem(
    modifier: Modifier = Modifier,
    notification: Notification
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(8.dp, 0.dp, 8.dp, 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(IntrinsicSize.Min)
                .background(ColorBgSecondary)
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                notification.content,
                modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                color = ColorText4,
                style = MaterialTheme.typography.titleLarge
            )
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
fun ContentItemPreview(modifier: Modifier = Modifier) {
    ProfilerTheme {
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = modifier.padding(8.dp, 0.dp)
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
                modifier = modifier.padding(8.dp, 0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .height(IntrinsicSize.Min)
                        .background(ColorBgSecondary)
                        .wrapContentHeight(Alignment.Top)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    val notification = Notification(0,"LLyyiok", "Development", "Android","https://avatars.githubusercontent.com/u/18302717?v=4", "Coding Rules", 3,"Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager")

                    Text(
                        notification.content,
                        modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal,
                        color = ColorText4,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}