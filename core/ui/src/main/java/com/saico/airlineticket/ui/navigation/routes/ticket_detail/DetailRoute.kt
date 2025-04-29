package com.saico.airlineticket.ui.navigation.routes.ticket_detail

import com.google.gson.Gson
import com.saico.airlineticket.model.FlightModel
import com.saico.airlineticket.ui.navigation.routes.Route
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed interface DetailRoute: Route {
    data object RootRoute: DetailRoute{
        override val analyticsTag = "detail-flow"
        override val route = "detail"
    }

    data object DetailScreenRoute: DetailRoute{
        override val analyticsTag = "detail-screen-flow"
        override val route = "detail/detail-screen/{flight}"

        fun createRoute(flight: FlightModel): String {
            val flightJson = URLEncoder.encode(Gson().toJson(flight), StandardCharsets.UTF_8.toString())
            return "detail/detail-screen/$flightJson"
        }
    }

}