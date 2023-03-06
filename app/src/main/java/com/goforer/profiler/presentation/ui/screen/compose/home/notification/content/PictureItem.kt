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

package com.goforer.profiler.presentation.ui.screen.compose.home.notification.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.goforer.profiler.R
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification
import com.goforer.profiler.presentation.ui.theme.ColorBgSecondary

@Composable
fun PictureItem(
    modifier: Modifier = Modifier,
    notification: Notification
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(8.dp, 0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(ColorBgSecondary)
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        ) {
            Text(
                stringResource(id = R.string.profile_detail_picture),
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                Modifier.size(width = 40.dp, height = 40.dp),
                CircleShape
            ) {
                Box {
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(notification.sender)
                            .crossfade(true)
                            .build()
                    )

                    Image(
                        painter = painter,
                        contentDescription = "ComposeTest",
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxSize()
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colorScheme.secondary, CircleShape),
                        Alignment.CenterStart,
                        contentScale = ContentScale.Crop
                    )

                    if (painter.state is AsyncImagePainter.State.Loading) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_profile_logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .align(Alignment.Center),
                        )
                    }
                }
            }
        }
    }
}