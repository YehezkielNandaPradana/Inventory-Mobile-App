package io.github.ComposeKit2.Helper.SuccessMessage

import androidx.compose.runtime.Composable

object SimpleDialog {

    var show: ((@Composable () -> Unit) -> Unit)? = null
    var close: (() -> Unit)? = null

    fun show(content: @Composable () -> Unit) {
        show?.invoke(content)
    }

    fun close() {
        close?.invoke()
    }
}