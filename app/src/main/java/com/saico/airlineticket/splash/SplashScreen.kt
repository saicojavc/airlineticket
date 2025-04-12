package com.saico.airlineticket.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.saico.airlineticket.R

@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        ConstraintLayout {
            val (backgroundImg, title, subtitle, startbtn) = createRefs()

            Image(
                painterResource(id = R.drawable.splash_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(backgroundImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            val styledText = buildAnnotatedString {
                append("Discover your\nDream")
                withStyle(
                    style = SpanStyle(color = colorResource(R.color.orange))
                ){
                    append("Flight")
                }
                append("\nEasily")
            }
            Text(
                text = styledText,
                fontSize = 53.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(horizontal = 16.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = stringResource(id = R.string.subtitle_splash),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.orange),
                modifier = Modifier
                    .padding(top = 32.dp, start = 16.dp)
                    .constrainAs(subtitle) {
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)
                    }
            )
        }

    }

}