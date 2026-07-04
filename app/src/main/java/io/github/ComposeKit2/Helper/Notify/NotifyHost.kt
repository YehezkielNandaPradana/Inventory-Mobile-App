package io.github.ComposeKit2.Helper.Notify

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppNotifierHost() {

    val visible = AppNotifier.visible
    val message = AppNotifier.message
    val type = AppNotifier.type

    LaunchedEffect(visible) {
        if (visible) {
            kotlinx.coroutines.delay(2500)
            AppNotifier.hide()
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = androidx.compose.animation.slideInVertically() +
                androidx.compose.animation.fadeIn(),
        exit = androidx.compose.animation.slideOutVertically() +
                androidx.compose.animation.fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 10.dp,
                color = when (type) {
                    NotifyType.SUCCESS -> Color(0xFF1B5E20)
                    NotifyType.ERROR -> Color(0xFFB71C1C)
                    NotifyType.INFO -> Color(0xFF0D47A1)
                    else -> Color(0xFF333333)
                }
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = when (type) {
                            NotifyType.SUCCESS -> Icons.Default.Check
                            NotifyType.ERROR -> Icons.Default.Close
                            NotifyType.INFO -> Icons.Default.Info
                            else -> Icons.Default.Notifications
                        },
                        contentDescription = null,
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = message,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}