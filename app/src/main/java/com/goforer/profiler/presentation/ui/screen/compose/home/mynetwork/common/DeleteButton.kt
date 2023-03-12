package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.common

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
internal inline fun DeleteIconButton(
    noinline onClick: () -> Unit,
    crossinline icon: @Composable () -> Unit,
    crossinline text: @Composable () -> Unit,
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
            .heightIn(36.dp),
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
fun DeleteIconButtonPreview(modifier: Modifier = Modifier) {
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
                .heightIn(36.dp),
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
                stringResource(id = R.string.placeholder_delete_yes),
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}