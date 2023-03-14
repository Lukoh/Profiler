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

package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.common

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Man
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
//import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest.Builder
import coil.compose.rememberAsyncImagePainter
import com.goforer.profiler.R
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.PersonItemState
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.rememberPersonItemState
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.members.SexIconButton
import com.goforer.profiler.presentation.ui.theme.ColorBgSecondary
import com.goforer.profiler.presentation.ui.theme.ColorText2
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun PersonItem(
    modifier: Modifier = Modifier,
    sexButtonVisible: Boolean,
    person: Person,
    index: Int,
    followedState: MutableState<Boolean>,
    onItemClicked: (item: Person, index: Int) -> Unit,
    onFollowed: (Person, Boolean) -> Unit,
    onSexViewed: (String) -> Unit,
    onMemberDeleted: (Int) -> Unit,
    onEstimated: (Int, Boolean) -> Unit,
    state: PersonItemState = rememberPersonItemState(onDismissedToEstimation = {
        onEstimated(person.id, it)
    }),
    onNavigateToDetailInfo: (Int) -> Unit
) {
    /*
     * The following code implements the requirement of advancing automatically
     * to the DetailInfo screen when person id is changed....
     * and the user wanted to continue with the next process.
     */
    /*
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val currentNavigateToDetailInfo by rememberUpdatedState(onNavigateToDetailInfo)
    var clikced by remember { mutableStateOf(false) }

    LaunchedEffect(clikced, lifecycle) {
        if (clikced) {
            snapshotFlow { person.id }
                .flowWithLifecycle(lifecycle)
                .collect {
                    currentNavigateToDetailInfo(it)
                }
        }
    }

     */

    if (!person.deleted) {
        if (state.visibleDeleteBoxState.value)
            state.heightDpState.value = 92.dp
        else
            state.heightDpState.value = 56.dp

        val verticalPadding = if (index == 0)
            0.dp
        else
            2.dp

        state.favorState.value = person.favor
        SwipeToDismiss(
            state = state.dismissState,
            modifier = modifier
                .fillMaxWidth()
                .height(state.heightDpState.value)
                .padding(8.dp, verticalPadding)
                .clip(RoundedCornerShape(4.dp)),
            dismissContent = {
                Surface(
                    modifier = modifier.padding(8.dp, 0.dp)
                ) {
                    Column {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .background(ColorBgSecondary)
                                    .fillMaxWidth()
                                    .heightIn(56.dp, 122.dp)
                                    .clickable {
                                        //clikced = true
                                        onNavigateToDetailInfo(person.id)
                                        onItemClicked(person, index)
                                    },
                            ) {
                                IconContainer {
                                    Box {
                                        val painter = rememberAsyncImagePainter(
                                            model = Builder(LocalContext.current)
                                                .data(person.profileImage)
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
                                                .border(
                                                    1.dp,
                                                    MaterialTheme.colorScheme.secondary,
                                                    CircleShape
                                                ),
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
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.wrapContentWidth()) {
                                    Text(
                                        person.name,
                                        modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp),
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        fontStyle = FontStyle.Normal,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(modifier = Modifier.wrapContentWidth()) {
                                        Surface(
                                            modifier = Modifier
                                                .widthIn(36.dp)
                                                .align(Alignment.CenterVertically),
                                            shape = MaterialTheme.shapes.small,
                                            shadowElevation = 1.dp
                                        ) {
                                            Text(
                                                person.sex,
                                                modifier = Modifier
                                                    .padding(6.dp, 0.dp)
                                                    .align(Alignment.CenterVertically),
                                                fontFamily = FontFamily.SansSerif,
                                                fontSize = 13.sp,
                                                fontStyle = FontStyle.Normal,
                                                color = ColorText2,
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp).width(8.dp))
                                        if (sexButtonVisible) {
                                            SexIconButton(
                                                onClick = {
                                                    onSexViewed(person.sex)
                                                },
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Default.Man,
                                                        contentDescription = null,
                                                    )
                                                },
                                                text = {
                                                    Text(
                                                        text = person.sex,
                                                        fontFamily = FontFamily.SansSerif,
                                                        fontSize = 6.sp,
                                                        fontStyle = FontStyle.Italic
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                                Spacer(
                                    modifier = Modifier.weight(1f)
                                )
                                Row(modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                    .background(ColorBgSecondary)
                                ) {
                                    followedState.value = person.followed
                                    Surface(
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        shape = MaterialTheme.shapes.small,
                                        color = ColorBgSecondary,
                                        shadowElevation = 1.dp
                                    ) {
                                        Text(
                                            stringResource(id = R.string.follower_check),
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .padding(0.dp, 2.dp, 0.dp, 2.dp),
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 11.sp,
                                            color = ColorText2,
                                            fontStyle = FontStyle.Normal
                                        )
                                    }

                                    Checkbox(
                                        checked = followedState.value,
                                        onCheckedChange = {
                                            followedState.value = it
                                            onFollowed(person, it)
                                        }
                                    )
                                    Image(
                                        painter = painterResource(id =
                                        if (state.favorState.value)
                                            R.drawable.ic_member_like
                                        else
                                            R.drawable.ic_member_dislike
                                        ),
                                        contentDescription = "favor",
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .wrapContentSize()
                                            .clickable { },
                                        Alignment.CenterStart
                                    )
                                }
                                if (!sexButtonVisible) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_delete),
                                        contentDescription = "delete the profile",
                                        modifier = Modifier
                                            .align(Alignment.Top)
                                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                            .wrapContentSize()
                                            .clickable { state.visibleDeleteBoxState.value = true },
                                        Alignment.CenterStart
                                    )
                                }
                            }

                            if (state.visibleDeleteBoxState.value) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .height(IntrinsicSize.Min)
                                        .background(ColorBgSecondary)
                                        .wrapContentHeight(Alignment.Top)
                                        .fillMaxWidth()
                                        .heightIn(min = 36.dp)
                                ) {
                                    Text(
                                        stringResource(id = R.string.profile_list_item_delete, person.name),
                                        modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp,
                                        fontStyle = FontStyle.Normal,
                                        style = MaterialTheme.typography.displaySmall
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    DeleteIconButton(
                                        onClick = {
                                            state.visibleDeleteBoxState.value = false
                                            onMemberDeleted(person.id)
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = null,
                                            )
                                        },
                                        text = {
                                            Text(
                                                stringResource(id = R.string.placeholder_delete_yes),
                                                fontFamily = FontFamily.SansSerif,
                                                fontSize = 13.sp,
                                                fontStyle = FontStyle.Italic
                                            )
                                        }
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))
                                    DeleteIconButton(
                                        onClick = {
                                            state.visibleDeleteBoxState.value = false
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = null,
                                            )
                                        },
                                        text = {
                                            Text(
                                                stringResource(id = R.string.placeholder_delete_no),
                                                fontFamily = FontFamily.SansSerif,
                                                fontSize = 13.sp,
                                                fontStyle = FontStyle.Italic
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            },
            background = {
                AnimatedContent(
                    targetState = Pair(state.dismissState.dismissDirection, true),
                    transitionSpec = {
                        fadeIn(
                            tween(0),
                            initialAlpha = if (targetState.second) 1f else 0f,
                        ) with fadeOut(
                            tween(0),
                            targetAlpha = if (targetState.second) .7f else 0f,
                        )
                    }
                ) { (direction, willDismiss) ->
                    val revealSize = remember { Animatable(if (willDismiss) 0f else 1f) }
                    val iconSize = remember { Animatable(if (willDismiss) .7f else 1f) }

                    LaunchedEffect(key1 = Unit, block = {
                        if (willDismiss) {
                            revealSize.snapTo(0f)
                            launch {
                                revealSize.animateTo(1f, animationSpec = tween(400))
                            }
                            iconSize.snapTo(0.8f)
                            iconSize.animateTo(
                                1.45f,
                                spring(
                                    dampingRatio = Spring.DampingRatioHighBouncy,
                                )
                            )
                            iconSize.animateTo(
                                1f,
                                spring(
                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                )
                            )
                        }
                    })
                    Box(
                        modifier = Modifier
                            .padding(8.dp, 0.dp)
                            .fillMaxSize()
                            .clip(
                                CirclePath(
                                    revealSize.value,
                                    direction == DismissDirection.StartToEnd
                                )
                            )
                            .background(
                                color = when (direction) {
                                    DismissDirection.StartToEnd -> if (willDismiss) Color(0xFFFF3399) else Color.Black
                                    DismissDirection.EndToStart -> if (willDismiss) Color(0xFF6666FF) else Color.Black
                                    else -> Color.Transparent
                                },
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .align(
                                    when (direction) {
                                        DismissDirection.StartToEnd -> Alignment.CenterStart
                                        else -> Alignment.CenterEnd
                                    }
                                )
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .scale(iconSize.value)
                                .offset {
                                    IntOffset(
                                        x = 0,
                                        y = (10 * (1f - iconSize.value)).roundToInt()
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            when (direction) {
                                DismissDirection.StartToEnd -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_dislike),
                                        colorFilter = ColorFilter.tint(if (willDismiss) Color.Black else Color(0xFFFF3399)),
                                        contentDescription = null
                                    )
                                }
                                DismissDirection.EndToStart -> {
                                    Image(
                                        painter =  painterResource(id = R.drawable.ic_like),
                                        colorFilter = ColorFilter.tint(if (willDismiss) Color.Black else Color(0xFF6666FF)),
                                        contentDescription = null
                                    )
                                }
                                else -> {}
                            }
                        }
                    }
                }
            }
        )
    }
}

class CirclePath(private val progress: Float, private val start: Boolean) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {

        val origin = Offset(
            x = if (start) 0f else size.width,
            y = size.center.y,
        )

        val radius = (sqrt(
            size.height * size.height + size.width * size.width
        ) * 1f) * progress

        return Outline.Generic(
            Path().apply {
                addOval(
                    Rect(
                        center = origin,
                        radius = radius,
                    )
                )
            }
        )
    }
}


@Composable
private fun IconContainer(content: @Composable () -> Unit) {
    Surface(
        Modifier.size(width = 40.dp, height = 40.dp),
        CircleShape
    ) {
        content()
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
fun PersonItemPreview() {
    ProfilerTheme {
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.padding(8.dp, 0.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(ColorBgSecondary)
                        .wrapContentHeight(Alignment.Top)
                        .fillMaxWidth()
                        .heightIn(56.dp, 122.dp)
                        .clickable {},
                ) {
                    IconContainer {
                        Box {
                            val painter = rememberAsyncImagePainter(
                                model = Builder(LocalContext.current)
                                    .data("https://avatars.githubusercontent.com/u/18302717?v=4")
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
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.wrapContentWidth()) {
                        Text(
                            "Lukoh",
                            modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp),
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Normal,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(modifier = Modifier.wrapContentWidth()) {
                            Surface(
                                modifier = Modifier
                                    .widthIn(36.dp)
                                    .align(Alignment.CenterVertically),
                                shape = MaterialTheme.shapes.small,
                                shadowElevation = 1.dp
                            ) {
                                Text(
                                    "남성",
                                    modifier = Modifier
                                        .padding(6.dp, 0.dp)
                                        .align(Alignment.CenterVertically),
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 13.sp,
                                    fontStyle = FontStyle.Normal,
                                    color = ColorText2,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp).width(8.dp))
                            SexIconButton(
                                onClick = {
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Man,
                                        contentDescription = null,
                                    )
                                },
                                text = {
                                    Text(
                                        text = "남성",
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 8.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Row(modifier = Modifier
                        .wrapContentWidth()
                        .padding(0.dp, 0.dp, 16.dp, 0.dp)
                        .background(ColorBgSecondary)
                    ) {
                        Surface(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            shape = MaterialTheme.shapes.small,
                            color = ColorBgSecondary,
                            shadowElevation = 1.dp
                        ) {
                            Text(
                                stringResource(id = R.string.follower_check),
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(0.dp, 2.dp, 0.dp, 2.dp),
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Normal,
                                fontSize = 11.sp,
                                color = ColorText2,
                                fontStyle = FontStyle.Normal
                            )
                        }

                        Checkbox(
                            checked = true,
                            onCheckedChange = {}
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_member_like),
                            contentDescription = "favor",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .wrapContentSize()
                                .clickable { },
                            Alignment.CenterStart
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "delete the profile",
                        modifier = Modifier
                            .align(Alignment.Top)
                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
                            .wrapContentSize()
                            .clickable { },
                        Alignment.CenterStart
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .background(ColorBgSecondary)
                        .wrapContentHeight(Alignment.Top)
                        .fillMaxWidth()
                        .heightIn(min = 36.dp)
                ) {
                    Text(
                        stringResource(id = R.string.profile_list_item_delete, "Lukoh"),
                        modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Normal,
                        style = MaterialTheme.typography.displaySmall
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    DeleteIconButton(
                        onClick = {
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                            )
                        },
                        text = {
                            Text(
                                stringResource(id = R.string.placeholder_delete_yes),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 13.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    DeleteIconButton(
                        onClick = {
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                            )
                        },
                        text = {
                            Text(
                                stringResource(id = R.string.placeholder_delete_no),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 13.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    )
                }
            }
        }
    }
}