package io.github.ComposeKit2.ViewModel.ApiRequest

object ApiRequest {

    suspend fun <T> execute(
        request: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        try {
            val result = request()
            onSuccess(result)
        } catch (e: Exception) {
            onError(e.message ?: "Unknown Error")
        }
    }
}