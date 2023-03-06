package com.goforer.profiler.presentation.ui.screen.compose.home.notification.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification
import com.goforer.profiler.presentation.ui.theme.ColorText1

@Composable
fun TitleItem(
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
                .background(Color.Transparent)
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        ) {
            Text(
                notification.title,
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                color = ColorText1,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}