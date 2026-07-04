package io.github.ComposeKit2.Helper.Fetch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T> CoroutineScope.fetchApi(
    state: MutableStateFlow<ApiResult<T>>,
    apiCall: suspend () -> T,
) {
    launch {

        state.value = ApiResult.Loading

        try {
            val result = withContext(Dispatchers.IO) {
                apiCall()
            }

            state.value = ApiResult.Success(result)

        } catch (e: Exception) {
            state.value = ApiResult.Error(
                e.message ?: "Koneksi gagal"
            )
        }
    }
}