package com.saico.airlineticket.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.saico.airlineticket.login.LoginScreen
import com.saico.airlineticket.ui.navigation.routes.login.LoginRoute

fun NavGraphBuilder.loginGraph(navHostController: NavHostController) {
    navigation(
        startDestination = LoginRoute.LoginScreenRoute.route,
        route = LoginRoute.RootRoute.route
    ){
        composable(route = LoginRoute.LoginScreenRoute.route) {
            LoginScreen(
                navController = navHostController,
            )
        }
    }
}