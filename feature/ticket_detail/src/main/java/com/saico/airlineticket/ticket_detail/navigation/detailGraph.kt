package com.saico.airlineticket.ticket_detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.saico.airlineticket.model.FlightModel
import com.saico.airlineticket.ticket_detail.TicketDetailScreen
import com.saico.airlineticket.ui.navigation.routes.ticket_detail.DetailRoute

fun NavGraphBuilder.detailGraph(navHostController: NavHostController){
    navigation(
        startDestination = DetailRoute.DetailScreenRoute.route,
        route = DetailRoute.RootRoute.route
    ){
        composable(
            route = DetailRoute.DetailScreenRoute.route,
            arguments = listOf(
                navArgument("flight"){type = NavType.StringType},
            )
        ) { backStackEntry ->
            val flightJson = backStackEntry.arguments?.getString("flight") ?: ""
            val flight = Gson().fromJson(flightJson, FlightModel::class.java)

            TicketDetailScreen(
                navHostController = navHostController,
                flight = flight
            )
        }
    }
}