package com.saico.airlineticket.home.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

    val items by viewModel.loadFiltered(from, to).observeAsState(emptyList())


    LaunchedEffect(from, to) {
        viewModel.loadFiltered(from, to)
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
        isLoading = items.isEmpty()
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.darkPurple2))
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.darkPurple2))
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
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
            if (isLoading){
                Box (
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }else{
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 100.dp, start = 16.dp, end = 16.dp)
                ) {
                    itemsIndexed(items){ index, item ->
                        FlightItem(
                            item = item,
                            index = index
                        )
                    }
                }
            }
        }

    }


}

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
    }
}


