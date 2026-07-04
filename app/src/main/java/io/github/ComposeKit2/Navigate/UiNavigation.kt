package io.github.ComposeKit2.Navigate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController

@Composable
fun <T> NavigateOnSuccess(
    state: T?,
    navController: NavController,
    routeBuilder: (T) -> String,
    clearBackStack: String? = null
) {
    LaunchedEffect(state) {
        state?.let {
            navController.navigate(
                routeBuilder(it)
            ) {
                clearBackStack?.let { route ->
                    popUpTo(route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}