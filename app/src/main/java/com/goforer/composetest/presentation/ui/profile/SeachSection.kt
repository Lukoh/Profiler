package com.goforer.composetest.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.goforer.composetest.R
import com.goforer.composetest.presentation.ui.base.customstate.EditableInputState
import com.goforer.composetest.presentation.ui.base.customstate.rememberEditableInputState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    state: EditableInputState = rememberEditableInputState(""),
    onSearched: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(colorResource(id = R.color.colorSystemBgSecondary))
            .wrapContentHeight(Alignment.Top)
            .fillMaxWidth()
    ) {
        TextField(
            value = if (state.isHint)
                ""
            else
                state.text,
            onValueChange = {
                state.text = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                placeholderColor = MaterialTheme.colorScheme.surface,
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = MaterialTheme.shapes.small,
            placeholder = {
                Text(stringResource(R.string.placeholder_search), color = LocalContentColor.current)
            },
            textStyle = MaterialTheme.typography.titleMedium.copy(color = LocalContentColor.current),
            modifier = modifier
                .weight(4f)
        )
        SearchIconButton(
            onClick = {
                onSearched(state.text)
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