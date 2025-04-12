package com.saico.airlineticket.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.saico.airlineticket.home.navigation.homeGraph
import com.saico.airlineticket.ui.navigation.Navigator
import com.saico.airlineticket.ui.theme.AirlineticketTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var showSplashScreen by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(3000)
                showSplashScreen = false
            }

            AirlineticketTheme {
               val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    MainContainer(
                        startDestination = viewModel.firstScreen,
                    )
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
        }
    }
}

