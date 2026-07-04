package io.github.ComposeKit2.Helper.PopUp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object DialogHelper {
    var confirmText by mutableStateOf("OK")
    var dismissText by mutableStateOf("Batal")
    var onCancel: (() -> Unit)? = null

    enum class DialogType {
        ALERT,
        CUSTOM
    }

    var show by mutableStateOf(false)
    var type by mutableStateOf(DialogType.ALERT)
    var title by mutableStateOf("")
    var message by mutableStateOf("")
    var content by mutableStateOf<(@Composable () -> Unit)?>(null)
    var onConfirm: (() -> Unit)? = null
    var onDismiss: (() -> Unit)? = null

    fun alert(
        title: String,
        message: String,
        confirmText: String = "OK",
        dismissText: String = "Batal",
        onConfirm: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        this.type = DialogType.ALERT
        this.title = title
        this.message = message
        this.content = null
        this.confirmText = confirmText
        this.dismissText = dismissText
        this.onConfirm = onConfirm
        this.onCancel = onCancel
        show = true
    }

    fun showCustom(
        content: @Composable () -> Unit
    ) {
        this.type = DialogType.CUSTOM
        this.title = ""
        this.message = ""
        this.onConfirm = null
        this.onCancel = null
        this.content = content
        show = true
    }

    fun close() {
        onDismiss?.invoke()

        show = false
        title = ""
        message = ""
        content = null
        onConfirm = null
        onCancel = null
        confirmText = "OK"
        dismissText = "Batal"
    }
}