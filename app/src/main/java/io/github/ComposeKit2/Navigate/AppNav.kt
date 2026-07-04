package io.github.ComposeKit2.Navigate

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.ComposeKit2.ViewModel.ViewModel.HomeViewModel
import io.github.ComposeKit2.ViewModel.ViewModel.RiwayatViewModel
import io.github.naxx.newinventaris2.ui.Activities.BerandaScreen.BerandaScreen
import io.github.naxx.newinventaris2.ui.Activities.RiwayatScreen.Screen.RiwayatScreen


@Composable
fun NavApp(
    homeViewModel: HomeViewModel,
    navController : NavHostController,
    riwayatViewModel: RiwayatViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "Beranda",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right
            )
        }
    ) {
        composable("Beranda"){ BerandaScreen(navController,homeViewModel) }
        composable("Riwayat"){ RiwayatScreen(navController,riwayatViewModel) }
    }
}