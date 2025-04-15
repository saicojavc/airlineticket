package com.saico.airlineticket.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.saico.airlineticket.domain.LocationModel
import com.saico.airlineticket.home.model.prepareBottomMenu
import com.saico.airlineticket.ui.R
import com.saico.airlineticket.ui.component.DropDown
import com.saico.airlineticket.ui.component.TopBar

@Composable
fun HomeScreen(
    navController: NavHostController,
) {

    val viewModel = HomeViewModel()

    val locations = remember { mutableStateListOf<LocationModel>() }
    val locationsNames: List<String> = locations.map { it.Name }
    var showLocationLoading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        viewModel.loadLocations().observeForever { result ->
            locations.clear()
            locations.addAll(result)
            showLocationLoading = false
        }

    }
    Content(
        locationsNames = locationsNames,
        showLocationLoading = showLocationLoading

    )
}

@Composable
fun Content(
    locationsNames: List<String>,
    showLocationLoading: Boolean
) {

    var from: String = ""
    var to: String = ""
    var classes: String = ""

    Scaffold(
        bottomBar = {
            BottomBar()
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.darkPurple2))
                .padding(paddingValues)
        ) {
            item {
                TopBar()
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .background(
                            colorResource(id = R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    Text(
                        text = "From",
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.orange),
                    )
                    DropDown(
                        items = locationsNames,
                        loadingIcon = painterResource(id = R.drawable.from_ic),
                        hint = "Select origin",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->

                        from = selectedItem
                    }
                    Text(
                        text = "To",
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.orange),
                    )
                    DropDown(
                        items = locationsNames,
                        loadingIcon = painterResource(id = R.drawable.to_ic),
                        hint = "Select destination",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->

                        from = selectedItem
                    }

                }
            }

        }

    }


}

@Composable
fun BottomBar() {
    val bottomMenuItemList = prepareBottomMenu()
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(
        containerColor = colorResource(id = R.color.darkPurple),
        tonalElevation = 3.dp
    ) {

        bottomMenuItemList.forEach { bottomMenuItem ->
            BottomNavigationItem(
                selected = (selectedItem == bottomMenuItem.label),
                onClick = {
                    selectedItem = bottomMenuItem.label
                    if (bottomMenuItem.label == "Cart") {

                    } else {
                        Toast.makeText(context, bottomMenuItem.label, Toast.LENGTH_SHORT).show()
                    }
                },
                icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = null,
                        tint = colorResource(id = R.color.orange),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(20.dp)
                    )
                }
            )
        }

    }
}