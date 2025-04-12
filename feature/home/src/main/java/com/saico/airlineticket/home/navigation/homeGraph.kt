package com.saico.airlineticket.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.saico.airlineticket.home.HomeScreen
import androidx.navigation.compose.composable
import com.saico.airlineticket.ui.navigation.routes.home.HomeRoute

fun NavGraphBuilder.homeGraph(navHostController: NavHostController){
    navigation(
        startDestination = HomeRoute.HomeScreenRoute.route,
        route = HomeRoute.RootRoute.route
    ){
        composable(route = HomeRoute.HomeScreenRoute.route) {
            HomeScreen(navHostController)
        }
    }
}