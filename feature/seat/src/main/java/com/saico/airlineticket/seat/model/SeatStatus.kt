package com.saico.airlineticket.seat.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class SeatStatus {
    AVAILABLE, SELECTED, UNAVAILABLE, EMPTY
}

 class Seat(
    status: SeatStatus,
    var name: String
){
    var status by mutableStateOf(status)
}