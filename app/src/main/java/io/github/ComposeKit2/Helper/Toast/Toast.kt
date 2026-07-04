package io.github.ComposeKit2.Helper.Toast

import android.content.Context
import android.widget.Toast

fun toast(
    context: Context,
    message: String
) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}