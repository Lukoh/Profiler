package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.common.item

import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.goforer.profiler.R
import com.goforer.profiler.data.model.datum.response.mynetwork.Person
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.PersonItemState
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.common.rememberPersonItemState
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.math.sqrt

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DismissBackgroundContent(
    person: Person,
    onEstimated: (Int, Boolean) -> Unit,
    state: PersonItemState = rememberPersonItemState(onDismissedToEstimation = {
        onEstimated(person.id, it)
    }),
) {
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