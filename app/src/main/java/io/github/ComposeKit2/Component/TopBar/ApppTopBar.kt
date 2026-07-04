package io.github.ComposeKit2.Component.TopBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val InfoMain = Color(0xFF0E3A4E)
val InfoSoft = Color(0xFFE0F2FE)
val InfoDark = Color(0xFF0284C7)
val TextMain = Color(0xFF1E293B)
val TextSecondary = Color(0xFF64748B)
val OnlineGreen = Color(0xFF22C55E)

@Composable
fun HomeTopBar(
    title: String = "MyApp",
    isOnline: Boolean = true,
    hasNotification: Boolean = false,
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            brush = Brush.horizontalGradient(
                                colors = listOf(InfoMain, InfoDark)
                            ),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp
                        )
                    ) {
                        append(title)
                    }
                },
                maxLines = 1
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Surface(
                    shape = CircleShape,
                    color = InfoSoft,
                    modifier = Modifier
                        .size(40.dp)
                        .border(2.dp, Color.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profil",
                        tint = InfoMain,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                    )
                }
                if (isOnline) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(OnlineGreen)
                            .border(2.dp, Color.White, CircleShape)
                    )
                }
            }
        }
    }
}