package com.saico.airlineticket.ui.navigation.routes.sea

import com.saico.airlineticket.ui.navigation.routes.Route

sealed interface SearchRoute : Route {

    data object RootRoute : SearchRoute {
        override val analyticsTag = "sea-flow"
        override val route = "sea"
    }

    data object SeaScreenRoute : SearchRoute {
        override val analyticsTag = "sea-screen-flow"
        override val route = "sea/sea-screen/{from}/{to}/{classes}/{adultPassenger}"

        fun createRoute(from: String, to: String, classes: String, adultPassenger: String): String {
            return "sea/sea-screen/$from/$to/$classes/$adultPassenger"
        }
    }
}