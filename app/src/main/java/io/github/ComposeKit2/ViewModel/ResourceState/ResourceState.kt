package io.github.ComposeKit2.ViewModel.ResourceState

import io.github.ComposeKit2.Helper.Fetch.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ResourceState<T>(
    initial: ApiResult<T> = ApiResult.Loading
) {

    private val _state = MutableStateFlow(initial)
    val state = _state.asStateFlow()

    fun loading() {
        _state.value = ApiResult.Loading
    }

    fun success(data: T) {
        _state.value = ApiResult.Success(data)
    }

    fun error(message: String) {
        _state.value = ApiResult.Error(message)
    }
}