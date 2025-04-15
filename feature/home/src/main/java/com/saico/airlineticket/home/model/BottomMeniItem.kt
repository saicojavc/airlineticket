package com.saico.airlineticket.home.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.saico.airlineticket.ui.R

data class BottomMeniItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMeniItem> {
    return listOf(
        BottomMeniItem(label = "Home", painterResource(id = R.drawable.bottom_btn1)),
        BottomMeniItem(label = "Cart", painterResource(id = R.drawable.bottom_btn2)),
        BottomMeniItem(label = "Favorite", painterResource(id = R.drawable.bottom_btn3)),
        BottomMeniItem(label = "Order", painterResource(id = R.drawable.bottom_btn4))
    )
}