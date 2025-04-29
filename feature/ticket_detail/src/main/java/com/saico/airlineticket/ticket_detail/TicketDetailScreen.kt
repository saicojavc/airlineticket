package com.saico.airlineticket.ticket_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.saico.airlineticket.model.FlightModel

@Composable
fun TicketDetailScreen(navHostController: NavHostController, flight: FlightModel) {
    Content()
}

@Composable
fun Content() {
    Text(text = "Content")

}