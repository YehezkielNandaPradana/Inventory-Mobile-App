package io.github.ComposeKit2.Helper.SuccessMessage

object ToastHelper {

    var show: ((String) -> Unit)? = null

    fun show(message: String) {
        show?.invoke(message)
    }
}