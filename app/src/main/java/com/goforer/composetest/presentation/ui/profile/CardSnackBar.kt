package com.goforer.composetest.presentation.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.composetest.R

@Composable
fun CardSnackBar(modifier: Modifier = Modifier, snackbarData: SnackbarData) {
    Card(
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.cardElevation(defaultElevation =  2.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .heightIn(48.dp)
    ) {
        Row(
            modifier = Modifier.paddingFromBaseline(28.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Default.Notifications, contentDescription = "", modifier = Modifier.weight(1.5f))
            Spacer(Modifier.weight(1f))
            Text(modifier = Modifier.weight(8f).align(Alignment.CenterVertically)
                .padding(6.dp, 2.dp, 6.dp, 2.dp), text = snackbarData.visuals.message,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = colorResource(id = R.color.purple_700),
                fontStyle = FontStyle.Normal)
        }
    }
}