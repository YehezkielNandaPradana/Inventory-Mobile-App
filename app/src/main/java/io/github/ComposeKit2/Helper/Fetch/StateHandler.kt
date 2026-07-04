package io.github.ComposeKit2.Helper.Fetch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun <T> StateHandler(
    state: ApiResult<T>,
    modifier: Modifier = Modifier,
    onErrorColor: Color = MaterialTheme.colorScheme.error,
    emptyMessage: String = "Data tidak ditemukan",
    onSuccess: @Composable (T) -> Unit
) {
    when (state) {
        is ApiResult.Idle -> {
        }

        is ApiResult.Loading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ApiResult.Error -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.message, color = onErrorColor)
            }
        }

        is ApiResult.Success -> {
            val data = state.data
            if (data is List<*> && data.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = emptyMessage)
                }
            } else {
                onSuccess(data)
            }
        }
    }
}