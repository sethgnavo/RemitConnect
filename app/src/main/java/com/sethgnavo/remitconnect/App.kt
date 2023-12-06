package com.sethgnavo.remitconnect

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sethgnavo.remitconnect.ui.navigation.Destinations
import com.sethgnavo.remitconnect.ui.screens.HomeScreen
import com.sethgnavo.remitconnect.ui.screens.MobileWalletsScreen
import com.sethgnavo.remitconnect.ui.screens.RecipientScreen
import com.sethgnavo.remitconnect.ui.screens.SendMoneyOptionsScreen
import com.sethgnavo.remitconnect.ui.screens.SendMoneyScreen
import com.sethgnavo.remitconnect.ui.screens.SendToAfricaScreen
import com.sethgnavo.remitconnect.ui.screens.SuccessScreen

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.HomeRoute) {
        composable(Destinations.HomeRoute) { HomeScreen(navController) }
        composable(Destinations.SendMoneyOptionsRoute,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) { SendMoneyOptionsScreen(navController) }
        composable(Destinations.SendToAfricaRoute) { SendToAfricaScreen(navController) }
        composable(Destinations.SendMoneyRoute) { SendMoneyScreen(navController = navController) }
        composable(Destinations.RecipientRoute) { RecipientScreen(navController) }
        composable(Destinations.SuccessRoute) { SuccessScreen(navController) }
        composable(Destinations.MobileWalletsRoute) { MobileWalletsScreen(navController) }
    }
}


fun enterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { 1000 }, // Slide in from right
        animationSpec = tween(300)
    )
}

fun exitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -1000 }, // Slide out to left
        animationSpec = tween(300)
    )
}

fun popEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -1000 }, // Slide in from left for pop
        animationSpec = tween(300)
    )
}

fun popExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { 1000 }, // Slide out to right for pop
        animationSpec = tween(300)
    )
}
