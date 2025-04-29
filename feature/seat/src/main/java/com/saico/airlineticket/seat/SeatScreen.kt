package com.saico.airlineticket.seat

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.saico.airlineticket.model.FlightModel
import com.saico.airlineticket.seat.model.Seat
import com.saico.airlineticket.seat.model.SeatStatus
import com.saico.airlineticket.ui.R
import com.saico.airlineticket.ui.component.GradientButton
import com.saico.airlineticket.ui.navigation.routes.ticket_detail.DetailRoute

@Composable
fun SeatScreen(
    navHostController: NavHostController,
    flight: FlightModel
) {

    Content(
        navHostController = navHostController,
        flight = flight,
        onConfirm = {

        }
    )
}

@Composable
fun Content(
    navHostController: NavHostController,
    flight: FlightModel,
    onConfirm: (FlightModel) -> Unit,

    ) {
    val seatList = remember { mutableStateListOf<Seat>() }
    val selectedSeatNames = remember { mutableStateListOf<String>() }

    var seatCount by remember { mutableIntStateOf(0) }
    var totalPrice by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(flight) {
        seatList.clear()
        seatList.addAll(generateSeatList(flight))
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * flight.Price
    }


    fun updatePriceAndCount() {
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * flight.Price
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkPurple2))
    ) {

        val (topSection, middleSection, bottomSection) = createRefs()

        TopSection(
            modifier = Modifier
                .constrainAs(topSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
            navController = navHostController
        )

        ConstraintLayout(
            modifier = Modifier
                .padding(top = 100.dp)
                .constrainAs(middleSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            val (airplane, seatGrid) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.airple_seat),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(airplane) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .padding(top = 240.dp)
                    .padding(horizontal = 64.dp)
                    .constrainAs(seatGrid) {
                        top.linkTo(parent.top)
                        start.linkTo(airplane.start)
                        end.linkTo(airplane.end)
                    }
            ) {
                items(seatList.size) { index ->
                    val seat = seatList[index]
                    val backgroundColor = when (seat.status) {
                        SeatStatus.AVAILABLE -> colorResource(id = R.color.green)
                        SeatStatus.SELECTED -> colorResource(id = R.color.orange)
                        SeatStatus.UNAVAILABLE -> colorResource(id = R.color.grey)
                        SeatStatus.EMPTY -> Color.Transparent
                    }
                    SeatItem(
                        seat = seat,
                        onSeatClick = {
                            when (seat.status) {
                                SeatStatus.AVAILABLE -> {
                                    seat.status = SeatStatus.SELECTED
                                    selectedSeatNames.add(seat.name)
                                }

                                SeatStatus.SELECTED -> {
                                    seat.status = SeatStatus.AVAILABLE
                                    selectedSeatNames.remove(seat.name)
                                }

                                else -> {

                                }
                            }
                            updatePriceAndCount()
                        }
                    )
                }

            }
        }
        BottomSection(
            seatCount = seatCount,
            selectedSeats = selectedSeatNames.joinToString(", "),
            totalPrice = totalPrice,
            onSeatClick = {
                if (seatCount > 0) {
                    flight.Passenger = selectedSeatNames.joinToString { ", " }
                    flight.Price = totalPrice
                    onConfirm(flight)
                    navHostController.navigate(
                        DetailRoute.DetailScreenRoute.createRoute(flight)
                    )
                } else {
                    Toast.makeText(
                        navHostController.context,
                        "Please select your seat",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .constrainAs(bottomSection) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun BottomSection(
    seatCount: Int,
    selectedSeats: String,
    totalPrice: Double,
    onSeatClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(colorResource(id = R.color.darkPurple))
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LegendItem(text = "Available", color = colorResource(id = R.color.green))
            LegendItem(text = "Selected", color = colorResource(id = R.color.orange))
            LegendItem(text = "Unavailable", color = colorResource(id = R.color.grey))
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "$seatCount Seats Selected",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (selectedSeats.isBlank())"+" else selectedSeats,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "$${String.format("%.0f", totalPrice)}",
                color = colorResource(id = R.color.orange),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        GradientButton(
            onClick = onSeatClick,
            text = "Confirm Seats"
        )
    }

}

@Composable
fun LegendItem(
    text: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}

@Composable
fun SeatItem(
    seat: Seat,
    onSeatClick: () -> Unit
) {
    val backgroundColor = when (seat.status) {
        SeatStatus.AVAILABLE -> colorResource(id = R.color.green)
        SeatStatus.SELECTED -> colorResource(id = R.color.orange)
        SeatStatus.UNAVAILABLE -> colorResource(id = R.color.grey)
        SeatStatus.EMPTY -> Color.Transparent
    }

    val textColor = when (seat.status) {
        SeatStatus.AVAILABLE -> Color.White
        SeatStatus.UNAVAILABLE -> Color.Gray
        SeatStatus.SELECTED -> Color.Black
        SeatStatus.EMPTY -> Color.Transparent
    }

    val clickableEnabled = seat.status == SeatStatus.AVAILABLE || seat.status == SeatStatus.SELECTED
    println(seat.status)
    Box(
        modifier = Modifier
            .size(28.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable(enabled = clickableEnabled) {
                onSeatClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seat.name,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun TopSection(
    modifier: Modifier,
    navController: NavHostController,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.darkPurple2))
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        val (backBtn, headerTitle, worldImg) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "Choose Seats",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp)
                .constrainAs(headerTitle) {
                    start.linkTo(backBtn.end, margin = 8.dp)
                    top.linkTo(backBtn.top)
                    bottom.linkTo(backBtn.bottom)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.world),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(worldImg) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

    }
}

fun generateSeatList(flight: FlightModel): List<Seat> {
    val seatList = mutableListOf<Seat>()
    val numberSeat = flight.NumberSeat + (flight.NumberSeat / 7) + 1
    val seatAlphabetMap = mapOf(
        0 to "A",
        1 to "B",
        2 to "C",
        4 to "D",
        5 to "E",
        6 to "F",
    )
    var row = 0
    for (i in 0 until numberSeat) {
        if (i % 7 == 0) {
            row++
        }
        if (i % 7 == 3) {
            seatList.add(Seat(SeatStatus.EMPTY, row.toString()))
        } else {
            val seatName = seatAlphabetMap[i % 7] + row
            val seatStatus = if (flight.ReservatedSeats.contains(seatName)) {
                SeatStatus.UNAVAILABLE
            } else {
                SeatStatus.AVAILABLE
            }
            seatList.add(Seat(seatStatus, seatName))
        }
    }
    return seatList
}
