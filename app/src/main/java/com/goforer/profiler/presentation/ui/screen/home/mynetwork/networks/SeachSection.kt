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

package com.goforer.profiler.presentation.ui.screen.home.mynetwork.networks

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.profiler.R
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks.EditableInputState
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks.rememberEditableInputState
import com.goforer.profiler.presentation.ui.component.SearchIconButton
import com.goforer.profiler.presentation.ui.theme.ColorSearchBarBorder
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme

@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    state: EditableInputState = rememberEditableInputState(""),
    onSearched: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val indicatorColor = if (isFocused) Color.Black else Color.Gray
    val indicatorWidth = 0.5.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(IntrinsicSize.Min)
            .background(Color.Transparent)
            .wrapContentHeight(Alignment.Top)
            .border(
                width = 1.dp,
                color = ColorSearchBarBorder,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .fillMaxWidth()
    ) {
        TextField(
            value = if (state.isHint)
                ""
            else
                state.textState,
            onValueChange = {
                state.textState = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.primary,
            ),
            shape = MaterialTheme.shapes.small,
            placeholder = {
                Text(stringResource(R.string.placeholder_search),  style = MaterialTheme.typography.titleMedium.copy(color = LocalContentColor.current))
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium
            ),
            modifier = modifier
                .weight(4f)
                .background(Color.Transparent)
                .drawBehind {
                    val strokeWidth = indicatorWidth.value * density
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        indicatorColor,
                        Offset(0f, y),
                        Offset(size.width, y),
                        strokeWidth
                    )
                }
        )
        SearchIconButton(
            modifier = modifier.padding(2.dp, 4.dp),
            onClick = {
                onSearched(state.textState)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            },
            text = {
                Text(
                    stringResource(id = R.string.placeholder_search),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        )
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
fun SearchSectionPreview(modifier: Modifier = Modifier) {
    ProfilerTheme {
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        val indicatorColor = if (isFocused) Color.Black else Color.Gray
        val indicatorWidth = 0.5.dp
        val state: EditableInputState = rememberEditableInputState("")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(IntrinsicSize.Min)
                .background(Color.Transparent)
                .wrapContentHeight(Alignment.Top)
                .border(
                    width = 1.dp,
                    color = ColorSearchBarBorder,
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .fillMaxWidth()
        ) {
            TextField(
                value = if (state.isHint)
                    ""
                else
                    state.textState,
                onValueChange = {
                    state.textState = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                ),
                shape = MaterialTheme.shapes.small,
                placeholder = {
                    Text(
                        stringResource(R.string.placeholder_search),
                        style = MaterialTheme.typography.titleMedium.copy(color = LocalContentColor.current)
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium
                ),
                modifier = modifier
                    .weight(4f)
                    .background(Color.Transparent)
                    .drawBehind {
                        val strokeWidth = indicatorWidth.value * density
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            indicatorColor,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }
            )
            SearchIconButton(
                modifier = modifier.padding(2.dp, 4.dp),
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                text = {
                    Text(
                        stringResource(id = R.string.placeholder_search),
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            )
        }
    }
}