package io.github.ComposeKit2.Helper.PopUp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider

var confirmText by mutableStateOf("OK")
var dismissText by mutableStateOf("Batal")

var onCancel: (() -> Unit)? = null

@Composable
fun DialogHost() {
    val show = DialogHelper.show
    val type = DialogHelper.type

    if (!show) return

    when (type) {
        DialogHelper.DialogType.ALERT -> {
            ModernAlertContent()
        }
        DialogHelper.DialogType.CUSTOM -> {
            ModernCustomContent()
        }
    }
}

@Composable
private fun ModernAlertContent() {

    Dialog(
        onDismissRequest = {
            DialogHelper.close()
        }
    ) {

        AnimatedVisibility(
            visible = DialogHelper.show,
            enter = scaleIn(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                ),
                initialScale = 0.8f
            ) + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val icon: ImageVector =
                        when {
                            DialogHelper.title.contains("error", true) ->
                                Icons.Rounded.Error

                            DialogHelper.title.contains("success", true) ->
                                Icons.Rounded.CheckCircle

                            DialogHelper.title.contains("warning", true) ->
                                Icons.Rounded.Warning

                            else ->
                                Icons.Rounded.Info
                        }

                    val iconColor =
                        when (icon) {
                            Icons.Rounded.Error ->
                                Color(0xFFFF5252)

                            Icons.Rounded.CheckCircle ->
                                Color(0xFF4CAF50)

                            Icons.Rounded.Warning ->
                                Color(0xFFFFC107)

                            else ->
                                MaterialTheme.colorScheme.primary
                        }

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(bottom = 16.dp)
                    )

                    Text(
                        text = DialogHelper.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = DialogHelper.message,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                DialogHelper.onCancel?.invoke()
                                DialogHelper.close()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(dismissText)
                        }
                        Button(
                            onClick = {
                                DialogHelper.onConfirm?.invoke()
                                DialogHelper.close()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(confirmText)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ModernCustomContent() {

    Dialog(
        onDismissRequest = {
            DialogHelper.close()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {

        val view = LocalView.current
        (view.parent as? DialogWindowProvider)
            ?.window
            ?.setDimAmount(0.4f)

        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                val content = DialogHelper.content

                if (content != null) {
                    content()
                } else {
                    Text("No Content", color = Color.Gray)
                }
            }
        }
    }
}