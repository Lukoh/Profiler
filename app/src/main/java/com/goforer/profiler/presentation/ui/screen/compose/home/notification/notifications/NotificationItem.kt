package com.goforer.profiler.presentation.ui.screen.compose.home.notification.notifications

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.goforer.profiler.R
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification
import com.goforer.profiler.presentation.ui.theme.*

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notification: Notification,
    index: Int,
    onItemClicked: (item: Notification, index: Int) -> Unit,
    onNavigateToDetailInfo: (Int) -> Unit
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
                .heightIn(min = 80.dp)
                .clickable {
                    onItemClicked(notification, index)
                    onNavigateToDetailInfo(notification.id)
                },
        ) {
            IconContainer {
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
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier
                .wrapContentWidth()
                .animateContentSize()
            ) {
                Row(modifier = Modifier.wrapContentWidth()) {
                    Text(
                        text = "${notification.division}${"  -  "}${notification.team}" ,
                        modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        color = ColorText3,
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.wrapContentWidth().animateContentSize()) {
                    Text(
                        "${notification.name}${" "}${stringResource(id = R.string.notification_posted)}${" : "}${notification.title}",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(0.dp, 2.dp, 6.dp, 2.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        color = ColorText1,
                        fontStyle = FontStyle.Normal
                    )
                }
            }
        }
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
fun MyNetworkSectionPreview() {
    ProfilerTheme {
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.padding(8.dp, 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(ColorBgSecondary)
                    .wrapContentHeight(Alignment.Top)
                    .fillMaxWidth()
                    .heightIn(min = 80.dp)
                    .clickable {},
            ) {
                IconContainer {
                    Box {
                        val painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
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
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .animateContentSize()
                ) {
                    Row(modifier = Modifier.wrapContentWidth()) {
                        Text(
                            text = "${"Development"}${"  -  "}${"Android"}",
                            modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp),
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Normal,
                            color = ColorText3,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Row(modifier = Modifier.wrapContentWidth().animateContentSize()) {
                        Text(
                            "${"Lukoh"}${" "}${stringResource(id = R.string.notification_posted)}${" : "}${"Coding Rules"}",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(0.dp, 2.dp, 6.dp, 2.dp),
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            color = ColorText1,
                            fontStyle = FontStyle.Normal
                        )
                    }
                }
            }
        }
    }
}