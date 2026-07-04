package io.github.ComposeKit2.Helper.Fetch

import androidx.compose.runtime.Composable

@Composable
fun <T> ApiEventHandler(
    state: ApiResult<T>,
    onSuccess: (T) -> Unit = {},
    onError: (String) -> Unit = {},
    onIdle: () -> Unit = {}
) {
    when (state) {

        is ApiResult.Success -> {
            onSuccess(state.data)
        }

        is ApiResult.Error -> {
            onError(state.message)
        }

        else -> {
            onIdle()
        }
    }
}