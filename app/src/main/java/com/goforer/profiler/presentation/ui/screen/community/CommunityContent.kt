package com.goforer.profiler.presentation.ui.screen.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.profiler.presentation.ui.ext.noRippleClickable

@Composable
fun CommunityContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(4.dp)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .wrapContentSize(Alignment.Center)
            .padding(0.dp, contentPadding.calculateTopPadding(), 0.dp, 0.dp)
            .noRippleClickable { }
    ) {
        Text(
            text = "is implementing....",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}