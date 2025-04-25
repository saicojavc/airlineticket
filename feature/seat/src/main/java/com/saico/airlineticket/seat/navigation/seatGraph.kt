package com.saico.airlineticket.seat.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.saico.airlineticket.seat.SeatScreen
import com.saico.airlineticket.ui.navigation.routes.seat.SeatRoute

fun NavGraphBuilder.seatGraph(navHostController: NavHostController){
    navigation(
        startDestination = SeatRoute.SeatScreenRoute.route,
        route = SeatRoute.RootRoute.route
    ){
        composable(route = SeatRoute.SeatScreenRoute.route){
            SeatScreen()
        }
    }
}