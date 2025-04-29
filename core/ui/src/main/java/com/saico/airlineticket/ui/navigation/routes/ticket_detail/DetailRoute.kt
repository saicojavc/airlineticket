package com.saico.airlineticket.ui.navigation.routes.ticket_detail

import com.saico.airlineticket.ui.navigation.routes.Route

sealed interface DetailRoute: Route {
    data object RootRoute: DetailRoute{
        override val analyticsTag = "detail-flow"
        override val route = "detail"
    }

    data object DetailScreenRoute: DetailRoute{
        override val analyticsTag = "detail-screen-flow"
        override val route = "detail/detail-screen"
    }

}