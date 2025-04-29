package com.saico.airlineticket.ticket_detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.saico.airlineticket.ticket_detail.TicketDetailScreen
import com.saico.airlineticket.ui.navigation.routes.ticket_detail.DetailRoute

fun NavGraphBuilder.detailGraph(navHostController: NavHostController){
    navigation(
        startDestination = DetailRoute.DetailScreenRoute.route,
        route = DetailRoute.RootRoute.route
    ){
        composable(route = DetailRoute.DetailScreenRoute.route) {
            TicketDetailScreen()
        }
    }
}