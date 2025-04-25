package com.saico.airlineticket.seat.model

enum class SeatStatus {
    AVAILABLE, SELECTED, UNAVAILABLE, EMPTY
}

data class Seat(
    var status: SeatStatus,
    var name: String
)