package com.saico.airlineticket.ui.navigation.routes.sea

import com.saico.airlineticket.ui.navigation.routes.Route

sealed interface SearhRoute : Route {

    data object RootRoute : SearhRoute {
        override val analyticsTag = "sea-flow"
        override val route = "sea"
    }

    data object SeaScreenRoute : SearhRoute {
        override val analyticsTag = "sea-screen-flow"
        override val route = "sea/sea-screen"
    }
}