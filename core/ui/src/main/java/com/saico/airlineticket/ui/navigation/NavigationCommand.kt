package com.saico.airlineticket.ui.navigation

import androidx.navigation.NavOptions

/**
 * Commands that can be emitted and the [NavigatorHandler] knows how to transform into a navigation
 * action in the main nav graph
 */
sealed interface NavigationCommand {
    /**
     * Indicates that no action is being processed
     */
    data object Idle : NavigationCommand

    /**
     * Pops the backstack one level, it effectively works as a back action in most cases
     */
    data object PopBackstack : NavigationCommand

    /**
     * Pop the backstack until it finds the route indicated in the params
     *
     * @param route Route until which the backstack will be pop
     * @param inclusive whether or not the route indicate should also be pop from the backstack
     */
    data class PopBackstackUntil(
        val route: String,
        val inclusive: Boolean = false,
    ) : NavigationCommand

    /**
     * Navigate to an specific route
     *
     * @param route Route to which we want to navigate
     * @param navOptions any additional nav options that we want to specify throw a [NavOptions] object
     */
    data class NavigateTo(
        val route: String,
        val navOptions: NavOptions? = null,
    ) : NavigationCommand
}