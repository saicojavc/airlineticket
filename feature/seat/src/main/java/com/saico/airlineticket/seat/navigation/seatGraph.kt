package com.saico.airlineticket.seat.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.saico.airlineticket.model.FlightModel
import com.saico.airlineticket.seat.SeatScreen
import com.saico.airlineticket.ui.navigation.routes.seat.SeatRoute

fun NavGraphBuilder.seatGraph(navHostController: NavHostController){
    navigation(
        startDestination = SeatRoute.SeatScreenRoute.route,
        route = SeatRoute.RootRoute.route
    ){
        composable(
            route = SeatRoute.SeatScreenRoute.route,
            arguments = listOf(
                navArgument("flight") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val flightJson = backStackEntry.arguments?.getString("flight") ?: ""
            val flight = Gson().fromJson(flightJson, FlightModel::class.java)

            SeatScreen(
                navHostController = navHostController,
                flight = flight
            )
        }

    }
}