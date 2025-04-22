package com.saico.airlineticket.home.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.saico.airlineticket.home.search.SearchScreen
import com.saico.airlineticket.ui.navigation.routes.sea.SearchRoute

fun NavGraphBuilder.searchGraph(navHostController: NavHostController){
    navigation(
        startDestination = SearchRoute.SeaScreenRoute.route,
        route = SearchRoute.RootRoute.route
    ){
        composable(
            route = SearchRoute.SeaScreenRoute.route,
            arguments = listOf(
                navArgument("from") { type = NavType.StringType },
                navArgument("to") { type = NavType.StringType },
                navArgument("classes") { type = NavType.StringType },
                navArgument("adultPassenger") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val from = backStackEntry.arguments?.getString("from") ?: ""
            val to = backStackEntry.arguments?.getString("to") ?: ""
            val classes = backStackEntry.arguments?.getString("classes") ?: ""
            val adultPassenger = backStackEntry.arguments?.getString("adultPassenger") ?: ""

            SearchScreen(navHostController,from, to, classes, adultPassenger)
        }

    }
}