package io.github.ComposeKit2.Helper.Notify

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AppNotifier {
    var type by mutableStateOf(NotifyType.INFO)
        private set
    private val _visible = androidx.compose.runtime.mutableStateOf(false)
    val visible: Boolean get() = _visible.value

    private val _message = androidx.compose.runtime.mutableStateOf("")
    val message: String get() = _message.value

    fun success(msg: String) {
        _message.value = msg
        _visible.value = true
    }

    fun error(msg: String) {
        _message.value = msg
        _visible.value = true
    }

    fun hide() {
        _visible.value = false
        _message.value = ""
    }
}