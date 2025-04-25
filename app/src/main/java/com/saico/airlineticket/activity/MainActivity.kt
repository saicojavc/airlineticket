package com.saico.airlineticket.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.saico.airlineticket.home.navigation.homeGraph
import com.saico.airlineticket.home.search.navigation.searchGraph
import com.saico.airlineticket.login.navigation.loginGraph
import com.saico.airlineticket.splash.SplashScreen
import com.saico.airlineticket.ui.theme.AirlineticketTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var showSplashScreen by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(1000)
                showSplashScreen = false
            }

            AirlineticketTheme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (showSplashScreen){
                        SplashScreen()
                    }else{
                        MainContainer(
                            startDestination = viewModel.firstScreen,
                        )
                    }
                }


            }
        }
    }
}

@Composable
private fun MainContainer(
    startDestination: String,

){
    val navController = rememberNavController()

    Column {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.weight(1.0f)
        ) {
            homeGraph(navController)

            loginGraph(navController)

            searchGraph(navController)
        }
    }
}

