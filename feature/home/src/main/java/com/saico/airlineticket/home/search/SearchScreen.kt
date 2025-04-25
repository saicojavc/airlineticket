package com.saico.airlineticket.home.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.saico.airlineticket.ui.R
import com.saico.airlineticket.model.FlightModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    from: String,
    to: String,
    classes: String,
    adultPassenger: String
) {

    val viewModel = SearchViewModel()

    val items by viewModel.loadFiltered("NewYork", "LosAngles").observeAsState(emptyList())


    LaunchedEffect(from, to) {
        viewModel.loadFiltered("NewYork", "LosAngles")
    }


    Content(
        from = from,
        to = to,
        classes = classes,
        adultPassenger = adultPassenger,
        items = items,
        navController = navController
    )
}

@Composable
fun Content(
    from: String,
    to: String,
    classes: String,
    adultPassenger: String,
    items: List<FlightModel>,
    navController: NavHostController
) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(items) {
        if (items.isNotEmpty()) {
            isLoading = false
        }
//        isLoading = items.isEmpty()
    }

//    Scaffold { paddingValues ->
//        Column(
//            modifier = Modifier
//                .background(color = colorResource(id = R.color.darkPurple2))
//                .padding(paddingValues),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
    ConstraintLayout(
        modifier = Modifier
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
            text = "Search Result",
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
    //showList
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(top = 100.dp, start = 16.dp, end = 16.dp)
        ) {
            itemsIndexed(items) { index, item ->
                FlightItem(
                    item = item,
                    index = index
                )
            }
        }
    }

//        }
//
//    }


}

@SuppressLint("DefaultLocale")
@Composable
fun FlightItem(item: FlightModel, index: Int) {
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {

            }
            .background(
                color = colorResource(id = R.color.lightPurple),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        val (logo, timeTxt, airplaneIcon, dashLine, priceTxt, seatIcon, classTxt, fromTxt, fromShortTxt, toTxt, toShortTxt) = createRefs()

        AsyncImage(
            model = item.AirlineLogo,
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = item.ArriveTime,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = colorResource(id = R.color.darkPurple2),
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(timeTxt) {
                    start.linkTo(parent.start)
                    top.linkTo(logo.bottom)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.line_airple_blue),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(airplaneIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(timeTxt.bottom)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(dashLine) {
                    start.linkTo(parent.start)
                    top.linkTo(airplaneIcon.bottom)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "$${String.format("%.2f", item.Price)}",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = colorResource(id = R.color.orange),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(priceTxt) {
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.seat_black_ic),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(seatIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = item.ClassSeat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = colorResource(id = R.color.darkPurple2),
            modifier = Modifier
                .constrainAs(classTxt) {
                    start.linkTo(seatIcon.end)
                    top.linkTo(seatIcon.top)
                    bottom.linkTo(seatIcon.bottom)
                }
        )
        Text(
            text = item.From,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(fromTxt) {
                    start.linkTo(parent.start)
                    top.linkTo(timeTxt.bottom)
                }
        )
        Text(
            text = item.FromShort,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(fromShortTxt) {
                    start.linkTo(fromTxt.start)
                    top.linkTo(fromTxt.bottom)
                    end.linkTo(fromTxt.end)
                }
        )
        Text(
            text = item.To,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(toTxt) {
                    top.linkTo(timeTxt.bottom)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = item.ToShort,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .constrainAs(toShortTxt) {
                    start.linkTo(toTxt.start)
                    top.linkTo(toTxt.bottom)
                    end.linkTo(toTxt.end)
                }
        )
    }
}


