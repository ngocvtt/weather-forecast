package com.ngocvtt.weatherforecast.model.weather

import java.text.SimpleDateFormat
import java.util.*

data class WeatherInfo(val date: Date, val temperature: Double, val pressure: Int, val humidity: Double, val description: String){
    val displayValue : String
    get() {
        val dateFormat = SimpleDateFormat(
            "EEE, dd MMM yyyy", Locale.ENGLISH
        )
        return "Date: ${dateFormat.format(date)}\nAverage temperature: ${String.format("%.0f", temperature)}\nPressure: $pressure\nHumidity: ${String.format("%.0f", humidity)}%\nDescription: $description"
    }
}