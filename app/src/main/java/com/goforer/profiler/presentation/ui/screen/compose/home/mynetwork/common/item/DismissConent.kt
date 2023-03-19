package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.common.item

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Man
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.decode.SvgDecoder
import coil.size.Size
import com.goforer.base.ui.compose.ImageCrossFade
import com.goforer.base.ui.compose.loadImagePainter
import com.goforer.profiler.R
import com.goforer.profiler.data.model.datum.local.notification.PersonItem
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.PersonItemState
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.rememberPersonItemState
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.common.DeleteIconButton
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.members.SexIconButton
import com.goforer.profiler.presentation.ui.theme.ColorBgSecondary
import com.goforer.profiler.presentation.ui.theme.ColorText2
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissContent(
    modifier: Modifier = Modifier,
    personItem: PersonItem,
    onItemClicked: (item: Person, index: Int) -> Unit,
    onFollowed: (Person, Boolean) -> Unit,
    onSexViewed: (String) -> Unit,
    onMemberDeleted: (Int) -> Unit,
    onEstimated: (Int, Boolean) -> Unit,
    state: PersonItemState = rememberPersonItemState(onDismissedToEstimation = {
        onEstimated(personItem.person.id, it)
    }),
    onNavigateToDetailInfo: (Int) -> Unit
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopContainer(
                modifier = modifier,
                personItem,
                onItemClicked = onItemClicked,
                onFollowed = onFollowed,
                onSexViewed = onSexViewed,
                onEstimated = onEstimated,
                state = state,
                onNavigateToDetailInfo = onNavigateToDetailInfo
            )

            if (state.visibleDeleteBoxState.value) {
                BottomContainer(
                    person = personItem.person,
                    onMemberDeleted = onMemberDeleted,
                    state = state
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopContainer(
    modifier: Modifier,
    personItem: PersonItem,
    onItemClicked: (item: Person, index: Int) -> Unit,
    onFollowed: (Person, Boolean) -> Unit,
    onSexViewed: (String) -> Unit,
    onEstimated: (Int, Boolean) -> Unit,
    state: PersonItemState = rememberPersonItemState(onDismissedToEstimation = {
        onEstimated(personItem.person.id, it)
    }),
    onNavigateToDetailInfo: (Int) -> Unit
) {
    personItem.followedState.value = personItem.person.followed
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .background(ColorBgSecondary)
            .wrapContentHeight(Alignment.CenterVertically)
            .fillMaxWidth()
            .heightIn(68.dp, 122.dp)
            .clickable {
                //clikced = true
                onNavigateToDetailInfo(personItem.person.id)
                onItemClicked(personItem.person, personItem.index)
            },
    ) {
        IconContainer {
            Box {
                val painter = loadImagePainter(
                    data = personItem.person.profileImage,
                    size = Size.ORIGINAL
                )

                ImageCrossFade(painter = painter, durationMillis = null)
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
                    val preloadPainter = loadImagePainter(
                        data = R.drawable.ic_profile_logo,
                        size = Size.ORIGINAL
                    )

                    Image(
                        painter = preloadPainter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.Center),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                personItem.person.name,
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
                        personItem.person.sex,
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

                if (personItem.sexButtonVisible) {
                    Spacer(modifier = Modifier.width(8.dp))
                    SexIconButton(
                        onClick = {
                            onSexViewed(personItem.person.sex)
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Man,
                                contentDescription = null,
                            )
                        },
                        text = {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                text = personItem.person.sex,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 10.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                stringResource(id = R.string.follower_check),
                modifier = Modifier.align(Alignment.CenterVertically),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = ColorText2,
                fontStyle = FontStyle.Normal
            )
            Checkbox(
                modifier = Modifier.align(Alignment.CenterVertically),
                checked = true,
                onCheckedChange = {
                    personItem.followedState.value = it
                    onFollowed(personItem.person, it)
                }
            )

            val likePainter = loadImagePainter(
                data = R.drawable.ic_member_like,
                size = Size.ORIGINAL
            )

            Image(
                painter = likePainter,
                contentDescription = "favor",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentSize()
                    .clickable { },
                Alignment.Center
            )
            if (!personItem.sexButtonVisible) {
                val deletePainter = loadImagePainter(
                    data = R.drawable.ic_delete,
                    factory = SvgDecoder.Factory(),
                    size = Size.ORIGINAL
                )

                Spacer(modifier = Modifier.width(16.dp))
                ImageCrossFade(painter = deletePainter, durationMillis = null)
                Image(
                    painter = deletePainter,
                    contentDescription = "delete the profile",
                    modifier = Modifier
                        .align(Alignment.Top)
                        .width(24.dp)
                        .height(24.dp)
                        .clickable { state.visibleDeleteBoxState.value = true },
                    Alignment.TopEnd
                )
            }
        }
    }
}

@Composable
fun BottomContainer(
    person: Person,
    onMemberDeleted: (Int) -> Unit,
    state: PersonItemState
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .background(ColorBgSecondary)
            .wrapContentHeight(Alignment.Top)
            .fillMaxWidth()
            .heightIn(min = 36.dp)
            .animateContentSize()
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
fun DismissContentPreview() {
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
                        .wrapContentHeight(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .heightIn(68.dp, 122.dp)
                        .clickable {},
                ) {
                    IconContainer {
                        Box {
                            val painter = loadImagePainter(
                                data = "https://avatars.githubusercontent.com/u/18302717?v=4",
                                size = Size.ORIGINAL
                            )

                            ImageCrossFade(painter = painter, durationMillis = null)
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
                                val preloadPainter = loadImagePainter(
                                    data = R.drawable.ic_profile_logo,
                                    size = Size.ORIGINAL
                                )

                                Image(
                                    painter = preloadPainter,
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
                            Spacer(modifier = Modifier.width(8.dp))
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
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        text = "남성",
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 10.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            stringResource(id = R.string.follower_check),
                            modifier = Modifier.align(Alignment.CenterVertically),
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Normal,
                            fontSize = 11.sp,
                            color = ColorText2,
                            fontStyle = FontStyle.Normal
                        )
                        Checkbox(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            checked = true,
                            onCheckedChange = {}
                        )

                        val likePainter = loadImagePainter(
                            data = R.drawable.ic_member_like,
                            size = Size.ORIGINAL
                        )

                        Image(
                            painter = likePainter,
                            contentDescription = "favor",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .wrapContentSize()
                                .clickable { },
                            Alignment.Center
                        )
                        val deletePainter = loadImagePainter(
                            data = R.drawable.ic_delete,
                            factory = SvgDecoder.Factory(),
                            size = Size.ORIGINAL
                        )

                        Spacer(modifier = Modifier.width(16.dp))
                        ImageCrossFade(painter = deletePainter, durationMillis = null)
                        Image(
                            painter = deletePainter,
                            contentDescription = "delete the profile",
                            modifier = Modifier
                                .align(Alignment.Top)
                                .width(24.dp)
                                .height(24.dp)
                                .clickable {  },
                            Alignment.TopEnd
                        )
                    }

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