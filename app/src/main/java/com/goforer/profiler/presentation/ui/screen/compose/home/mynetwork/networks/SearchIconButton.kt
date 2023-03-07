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

package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.networks

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.profiler.R
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme

@Composable
fun SearchIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    /*
    val isButtonEnabled = remember { mutableStateOf(true) }
    val animatedButtonColor = animateColorAsState(
        targetValue = if (isButtonEnabled.value) Color.White else Color.Gray,
        animationSpec = tween(1000, 0, LinearEasing))

     */

    Button(
        onClick = onClick,
        modifier = modifier
            .wrapContentWidth()
            .heightIn(54.dp),
        shape = MaterialTheme.shapes.small,
        interactionSource = interactionSource
    ) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Row(modifier = Modifier.animateContentSize()) {
                    icon()
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        text()
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
fun SearchIconButtonPreview(modifier: Modifier = Modifier) {
    ProfilerTheme {
        val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        /*
        val isButtonEnabled = remember { mutableStateOf(true) }
        val animatedButtonColor = animateColorAsState(
            targetValue = if (isButtonEnabled.value) Color.White else Color.Gray,
            animationSpec = tween(1000, 0, LinearEasing))

         */


        Button(
            onClick = {},
            modifier = modifier
                .height(IntrinsicSize.Min)
                .wrapContentHeight()
                .wrapContentWidth()
                .heightIn(54.dp),
            shape = MaterialTheme.shapes.small,
            interactionSource = interactionSource
        ) {
            AnimatedVisibility(visible = isPressed) {
                if (isPressed) {
                    Row(modifier = Modifier.animateContentSize()) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    }
                }
            }
            Text(
                stringResource(id = R.string.placeholder_search),
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}