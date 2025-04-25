package com.saico.airlineticket.home.component

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.saico.airlineticket.ui.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun DatePickerDialog(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val dataFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    val departureCalendar = remember { Calendar.getInstance() }
    val returnCalendar = remember { Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) } }
    var departureDate by remember { mutableStateOf( dataFormat.format(departureCalendar.time)) }
    var returnDate by remember { mutableStateOf(dataFormat.format(returnCalendar.time)) }

    Row {
        DatePickerItem(
            modifier = modifier,
            dateText = departureDate,
            onDateSelected = { selectedDate ->
                departureDate = selectedDate
            },
            dataFormat = dataFormat,
            calendar = departureCalendar,
            context = context

        )

        Spacer(modifier = Modifier.width(4.dp))

        DatePickerItem(
            modifier = modifier,
            dateText = returnDate,
            onDateSelected = { selectedDate ->
                returnDate = selectedDate
            },
            dataFormat = dataFormat,
            calendar = returnCalendar,
            context = context

        )
    }
}

@Composable
fun DatePickerItem(
    modifier: Modifier = Modifier,
    dateText: String,
    onDateSelected: (String) -> Unit,
    dataFormat: SimpleDateFormat,
    calendar: Calendar,
    context: Context
) {
    Row(
        modifier = modifier
            .height(60.dp)
            .padding(top = 8.dp)
            .background(
                color = colorResource(id = R.color.lightPurple),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                showDatePickerDialog(
                    context = context,
                    calendar = calendar,
                    dataFormat = dataFormat,
                    onDateSelected = onDateSelected
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.calendar_ic),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(24.dp)
        )
        Text(
            text = dateText,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }
}


fun showDatePickerDialog(
    context: Context,
    calendar: Calendar,
    dataFormat: SimpleDateFormat,
    onDateSelected: (String) -> Unit
) {
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            calendar.set(selectedYear, selectedMonth, selectedDay)
            val formattedDate = dataFormat.format(calendar.time)
            onDateSelected(formattedDate)
        },
        year, month, day).show()
}