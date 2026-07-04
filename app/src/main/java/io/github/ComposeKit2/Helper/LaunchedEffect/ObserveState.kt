package io.github.ComposeKit2.Helper.LaunchedEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun <T> ObserveApiState(
    state: T?,
    error: String?,
    onSuccess: suspend (T) -> Unit,
    onError: suspend (String) -> Unit = {}
) {
    LaunchedEffect(state) {
        state?.let {
            onSuccess(it)
        }
    }

    LaunchedEffect(error) {
        error?.let {
            onError(it)
        }
    }
}