package io.github.ComposeKit2.ViewModel.ResourceState

import com.google.gson.Gson
import retrofit2.HttpException
import kotlin.jvm.java

suspend fun <T> ResourceState<T>.execute(
    request: suspend () -> T,
    onSuccess: (T) -> Unit = {},
    onError: (String) -> Unit = {}
) {
    loading()
    try {
        val result = request()

        success(result)
        onSuccess(result)

    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()

        val message = try {
            Gson().fromJson(
                errorBody,
                ErrorResponse::class.java
            ).message
        } catch (ex: Exception) {
            e.message()
        }
        onError(message)
    }
}