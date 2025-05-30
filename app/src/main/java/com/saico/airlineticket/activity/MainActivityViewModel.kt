package com.saico.airlineticket.activity


import androidx.lifecycle.ViewModel
import com.saico.airlineticket.ui.navigation.Navigator
import com.saico.airlineticket.ui.navigation.routes.home.HomeRoute
import com.saico.airlineticket.ui.navigation.routes.login.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainActivityViewModel @Inject constructor() : ViewModel() {

    val firstScreen = LoginRoute.RootRoute.route

}