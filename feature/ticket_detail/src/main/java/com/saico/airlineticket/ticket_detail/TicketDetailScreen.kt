package com.saico.airlineticket.ticket_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.saico.airlineticket.model.FlightModel
import com.saico.airlineticket.ui.R
import com.saico.airlineticket.ui.component.GradientButton

@Composable
fun TicketDetailScreen(
    navHostController: NavHostController,
    flight: FlightModel
) {
    Content(
        navHostController = navHostController,
        flight = flight
    )
}


@Composable
fun Content(
    navHostController: NavHostController,
    flight: FlightModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkPurple2))
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(colorResource(id = R.color.darkPurple2))
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.darkPurple2))
            ) {
                val (topSection, ticketDetail) = createRefs()

                TopSection(
                    modifier = Modifier
                        .constrainAs(topSection) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)

                        },
                    navController = navHostController
                )

                TicketDetailContent(
                    flight = flight,
                    modifier = Modifier
                        .constrainAs(ticketDetail) {
                            top.linkTo(parent.top, margin = 90.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }

            GradientButton(
                text = "Confirm Ticket",
                onClick = {

                }
            )
        }
    }

}

@Composable
fun TicketDetailContent(
    flight: FlightModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .background(
                color = colorResource(id = R.color.lightPurple),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            val (logo, arrivalText, lineImg, fromText, fromShortText, toText, toShortTxt) = createRefs()

            AsyncImage(
                model = flight.AirlineLogo,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .constrainAs(logo) {
                        top.linkTo(parent.top, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Fit
            )
            Text(
                text = flight.ArriveTime,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.darkPurple2),
                modifier = Modifier
                    .constrainAs(arrivalText) {
                        top.linkTo(logo.bottom, margin = 8.dp)
                        start.linkTo(logo.start)
                        end.linkTo(logo.end)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.line_airple_blue),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(lineImg) {
                        top.linkTo(arrivalText.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "from",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(fromText) {
                        top.linkTo(arrivalText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(lineImg.start)
                    }
            )
            Text(
                text = flight.FromShort,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(fromShortText) {
                        top.linkTo(fromText.bottom, margin = 8.dp)
                        start.linkTo(fromText.start)
                        end.linkTo(fromText.end)
                        bottom.linkTo(fromText.bottom)
                    }
            )
            Text(
                text = "to",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(toText) {
                        top.linkTo(arrivalText.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(lineImg.end)
                    }
            )
            Text(
                text = flight.ToShort,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(toShortTxt) {
                        top.linkTo(toText.bottom, margin = 8.dp)
                        start.linkTo(toText.start)
                        end.linkTo(toText.end)
                        bottom.linkTo(fromText.bottom)
                    }
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    text = "From",
                    color = Color.Black,
                )
                Text(
                    text = flight.From,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Date",
                    color = Color.Black,
                )
                Text(
                    text = flight.Date,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    text = "To",
                    color = Color.Black,
                )
                Text(
                    text = flight.To,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Time",
                    color = Color.Black,
                )
                Text(
                    text = flight.Time,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    text = "Class",
                    color = Color.Black,
                )
                Text(
                    text = flight.ClassSeat,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Seats",
                    color = Color.Black,
                )
                Text(
                    text = flight.Passenger,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    text = "Airline",
                    color = Color.Black,
                )
                Text(
                    text = flight.AirlineName,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Price",
                    color = Color.Black,
                )
                Text(
                    text = "$${String.format("%.2f", flight.Price)}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            Image(
                painter = painterResource(id = R.drawable.qrcode),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 8.dp),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.barcode),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentScale = ContentScale.FillWidth
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