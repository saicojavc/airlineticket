package com.saico.airlineticket.ui.navigation.routes.seat

import com.saico.airlineticket.ui.navigation.routes.Route

sealed interface SeatRoute: Route {

    data object RootRoute: SeatRoute{
        override val analyticsTag = "seat-flow"
        override val route = "seat"
    }

    data object SeatScreenRoute: SeatRoute{
        override val analyticsTag = "seat-screen-flow"
        override val route = "seat/seat-screen"
    }
}