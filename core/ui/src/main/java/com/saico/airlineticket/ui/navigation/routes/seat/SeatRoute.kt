package com.saico.airlineticket.ui.navigation.routes.seat

import com.google.gson.Gson
import com.saico.airlineticket.model.FlightModel
import com.saico.airlineticket.ui.navigation.routes.Route
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed interface SeatRoute: Route {

    data object RootRoute: SeatRoute{
        override val analyticsTag = "seat-flow"
        override val route = "seat"
    }

    data object SeatScreenRoute: SeatRoute{
        override val analyticsTag = "seat-screen-flow"
        override val route = "seat/seat-screen/{flight}"

        fun createRoute(flight: FlightModel): String {
            val flightJson = URLEncoder.encode(Gson().toJson(flight), StandardCharsets.UTF_8.toString())
            return "seat/seat-screen/$flightJson"
        }
    }
}