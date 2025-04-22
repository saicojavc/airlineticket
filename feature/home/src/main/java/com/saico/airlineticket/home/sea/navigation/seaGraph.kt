package com.saico.airlineticket.home.sea.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.saico.airlineticket.home.sea.SearchScreen
import com.saico.airlineticket.ui.navigation.routes.sea.SearhRoute

fun NavGraphBuilder.searchGraph(navHostController: NavHostController){
    navigation(
        startDestination = SearhRoute.SeaScreenRoute.route,
        route = SearhRoute.RootRoute.route
    ){
        composable(route = SearhRoute.SeaScreenRoute.route){
            SearchScreen()
        }
    }
}