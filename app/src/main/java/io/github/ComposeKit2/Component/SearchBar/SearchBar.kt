package io.github.ComposeKit2.Component.SearchBar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.ComposeKit2.Component.TopBar.InfoMain
import io.github.ComposeKit2.Component.TopBar.TextMain
import io.github.ComposeKit2.Component.TopBar.TextSecondary
import io.github.naxx.newinventaris2.ui.theme.SurfaceWhite


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Cari sesuatu...",
    onFocusChanged: ((Boolean) -> Unit)? = null
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isFocused) Color.White else SurfaceWhite,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )
    val elevation by animateDpAsState(
        targetValue = if (isFocused) 12.dp else 4.dp,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )
    val borderColor by animateColorAsState(
        targetValue = if (isFocused) InfoMain else Color.Transparent,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )
    val iconColor by animateColorAsState(
        targetValue = if (isFocused) InfoMain else TextSecondary,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    Surface(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(16.dp),
                ambientColor = InfoMain.copy(alpha = 0.15f),
                spotColor = InfoMain.copy(alpha = 0.15f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(borderColor)
            .padding(1.5.dp),
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )

            Spacer(Modifier.width(12.dp))

            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = TextStyle(
                    color = TextMain,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                        onFocusChanged?.invoke(focusState.isFocused)
                    },
                cursorBrush = SolidColor(InfoMain),
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = TextSecondary,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    innerTextField()
                }
            )

            if (query.isNotEmpty()) {
                Spacer(Modifier.width(8.dp))
                IconButton(
                    onClick = { onQueryChange("") },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Hapus",
                        tint = TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}