package io.github.ComposeKit2.Helper.Animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

object AnimatedScreenTransition {

    private const val DURATION = 300

    val slideInRight: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(DURATION)
        )
    }

    val slideOutLeft: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(DURATION)
        )
    }

    val slideInLeft: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(DURATION)
        )
    }

    val slideOutRight: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(DURATION)
        )
    }

    val fadeIn: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        androidx.compose.animation.fadeIn(
            animationSpec = tween(DURATION)
        )
    }

    val fadeOut: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        androidx.compose.animation.fadeOut(
            animationSpec = tween(DURATION)
        )
    }
}