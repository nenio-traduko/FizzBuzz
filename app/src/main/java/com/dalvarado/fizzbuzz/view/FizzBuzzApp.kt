package com.dalvarado.fizzbuzz.view

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FizzBuzzApp() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = "request_view"
  ) {
    composable("request_view") {
      SequenceRequestView(onViewSequence = { navController.navigate("sequence_view") })
    }
    composable(
      "sequence_view",
      enterTransition = {
        fadeIn(
          animationSpec = tween(
            300, easing = LinearEasing
          )
        ) + slideIntoContainer(
          animationSpec = tween(300, easing = EaseIn),
          towards = AnimatedContentTransitionScope.SlideDirection.Start
        )
      },
      exitTransition = {
        fadeOut(
          animationSpec = tween(
            300, easing = LinearEasing
          )
        ) + slideOutOfContainer(
          animationSpec = tween(300, easing = EaseOut),
          towards = AnimatedContentTransitionScope.SlideDirection.End
        )
      }
    ) {
      SequenceView(onBack = { navController.popBackStack() })
    }
  }
}